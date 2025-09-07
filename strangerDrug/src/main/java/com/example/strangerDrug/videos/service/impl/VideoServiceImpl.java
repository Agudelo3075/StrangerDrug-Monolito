package com.example.strangerDrug.videos.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.strangerDrug.videos.model.Video;
import com.example.strangerDrug.videos.service.VideoService;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService{

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final Map<String, Video> videoStorage = new ConcurrentHashMap<>();
    
    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Override
    public Video uploadVideo(MultipartFile videoFile, MultipartFile previewFile, String title, String description) {
        try {
         
            String videoId = UUID.randomUUID().toString();

            validateVideoFile(videoFile);
            if (previewFile != null) {
                validatePreviewFile(previewFile);
            }

            String videoS3Key = "videos/" + videoId + "/" + videoFile.getOriginalFilename();
            String previewS3Key = previewFile != null ? "previews/" + videoId + "/" + previewFile.getOriginalFilename() : null;

            Video video = new Video(videoId, title, description, videoFile.getOriginalFilename(),
                                    videoFile.getContentType(), videoFile.getSize(), videoS3Key);

            if (previewFile != null) {
                video.setPreviewFileName(previewFile.getOriginalFilename());
                video.setPreviewS3Key(previewS3Key);
            }

            uploadFileToS3(videoFile, videoS3Key);

            if (previewFile != null) {
                uploadFileToS3(previewFile, previewS3Key);
            }

            video.setStatus(Video.VideoStatus.READY);

            videoStorage.put(videoId, video);

            return video;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload video");
        }
    }

    @Override
    public Optional<Video> getVideo(String id) {
        return Optional.ofNullable(videoStorage.get(id));
    }

    @Override
    public List<Video> getAllVideos() {
        return new ArrayList<>(videoStorage.values());
    }

    @Override
    public String getVideoUrl(String id) {
        Video video = videoStorage.get(id);
        if (video == null) {
            throw new RuntimeException("Video not found: " + id);
        }

        return generatePresignedUrl(video.getS3Key());
    }

    @Override
    public String getPreviewUrl(String id) {
       Video video = videoStorage.get(id);
       if (video == null || video.getPreviewS3Key() == null) {
            throw new RuntimeException("Preview not found for video: " + id);
       }

       return generatePresignedUrl(video.getPreviewS3Key());
    }

    @Override
    public void deleteVideo(String id) {
        Video video = videoStorage.get(id);
        if (video == null) {
            throw new RuntimeException("Video not found");
        }

        try {
            deleteFileFromS3(video.getS3Key());
            if (video.getPreviewS3Key() != null) {
                deleteFileFromS3(video.getPreviewS3Key());
            }

            videoStorage.remove(id);

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete video: " + e.getMessage());
        }
    }

    private void deleteFileFromS3(String s3Key) {
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .build();

        s3Client.deleteObject(deleteRequest);
    }


    private String generatePresignedUrl(String s3Key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofHours(1)) // URL válida por 1 hora
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);
        return presignedRequest.url().toString();
    }

    //Buscar explicación
    private void uploadFileToS3(MultipartFile file, String s3Key)throws Exception{
        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .build();

        s3Client.putObject(putRequest, RequestBody.fromBytes(file.getBytes()));
    }

    private void validateVideoFile(MultipartFile file){
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Video file is required");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("video/")) {
            throw new IllegalArgumentException("File must video");
        }

        if (file.getSize() > 500 * 1024 * 1024) {
            throw new IllegalArgumentException("Video file too large. Maximum size");
        }
    }

    private void validatePreviewFile(MultipartFile file){
        if (file == null || file.isEmpty()) {
            return;
        }

        String contentType = file.getContentType();
        if (contentType == null || (!contentType.startsWith("image/") && !contentType.startsWith("video/"))) {
            throw new IllegalArgumentException("Preview file must be an image or video");
        }

        if (file.getSize() > 50 * 1024 * 1024) {
            throw new IllegalArgumentException("Preview file too large. Maximum size is 50MB");
        }
    }
}
