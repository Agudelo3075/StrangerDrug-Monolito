package com.example.strangerDrug.videos.service;

import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import com.example.strangerDrug.videos.model.Video;

public interface VideoService {
    Video uploadVideo(MultipartFile videoFile, MultipartFile previewFile, String title, String description);
    Optional<Video> getVideo(String id);
    List<Video> getAllVideos();
    String getVideoUrl(String id);
    String getPreviewUrl(String id);
    void deleteVideo(String id);
}
