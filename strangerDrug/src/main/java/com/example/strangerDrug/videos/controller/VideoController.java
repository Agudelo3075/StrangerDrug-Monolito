package com.example.strangerDrug.videos.controller;

import com.example.strangerDrug.videos.service.*;
import com.example.strangerDrug.videos.dto.ErrorResponse;
import com.example.strangerDrug.videos.dto.VideoUploadResponse;
import com.example.strangerDrug.videos.model.Video;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/videos")
@Tag(name = "Video Management", description = "API for uploading and managing videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload a video with optional preview")
    @ApiResponse(responseCode = "200", description = "Video uploaded successfully")
    @ApiResponse(responseCode = "400", description = "Invalid file or parameters")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<?> uploadVideo(
        @Parameter(description = "Video file to upload") @RequestParam("video") MultipartFile videoFile,
        @Parameter(description = "Preview file (image or short video)") @RequestParam(value = "preview", required = false)
        MultipartFile previewFile,
        @Parameter(description = "Video title") @RequestParam("title") String title,
        @Parameter(description = "Video description") @RequestParam(value = "description", defaultValue = "") 
        String description) {
     
        try {
            Video video = videoService.uploadVideo(videoFile, previewFile, title, description);

            String videoUrl = videoService.getVideoUrl(video.getId());
            String previewUrl = video.getPreviewS3Key() != null ? videoService.getPreviewUrl(video.getId()) : null;

            VideoUploadResponse response = new VideoUploadResponse(video, videoUrl, previewUrl);
            
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse("VALIDATION_ERROR", e.getMessage(), 400);
            return ResponseEntity.badRequest().body(error);
        } catch (Exception e){
            ErrorResponse error = new ErrorResponse("UPLOAD_ERROR", "Failed to upload video", 500);
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get video information by ID")
    public ResponseEntity<?> getVideo(@PathVariable String id){
        try {
            
            Optional<Video> video = videoService.getVideo(id);

            if (video.isEmpty()) {
                ErrorResponse error = new ErrorResponse("NOT_FOUND", "Video not found", 404);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            String videoUrl = videoService.getVideoUrl(id);
            String previewUrl = video.get().getPreviewS3Key() != null ? videoService.getPreviewUrl(id) : null;
            
            VideoUploadResponse response = new VideoUploadResponse(video.get(), videoUrl, previewUrl);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse("SERVER_ERROR", "Failed to get video", 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping
    @Operation(summary = "Get all videos")
    public ResponseEntity<?> getAllVideos() {
        try {
            List<Video> videos = videoService.getAllVideos();
            
            List<VideoUploadResponse> responses = videos.stream()
                    .map(video -> {
                        String videoUrl = videoService.getVideoUrl(video.getId());
                        String previewUrl = video.getPreviewS3Key() != null ? videoService.getPreviewUrl(video.getId()) : null;
                        return new VideoUploadResponse(video, videoUrl, previewUrl);
                    })
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(responses);
            
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse("SERVER_ERROR", "Failed to get videos", 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/stream/{id}")
    @Operation(summary = "Get video streaming URL")
    public ResponseEntity<?> getVideoStreamUrl(@PathVariable String id) {
        try {
            String videoUrl = videoService.getVideoUrl(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", videoUrl)
                    .build();
                    
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse("NOT_FOUND", e.getMessage(), 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @GetMapping("/preview/{id}")
    @Operation(summary = "Get preview streaming URL")
    public ResponseEntity<?> getPreviewStreamUrl(@PathVariable String id) {
        try {
            String previewUrl = videoService.getPreviewUrl(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", previewUrl)
                    .build();
                    
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse("NOT_FOUND", e.getMessage(), 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a video")
    public ResponseEntity<?> deleteVideo(@PathVariable String id) {
        try {
            videoService.deleteVideo(id);
            return ResponseEntity.ok().build();
            
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse("NOT_FOUND", e.getMessage(), 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
