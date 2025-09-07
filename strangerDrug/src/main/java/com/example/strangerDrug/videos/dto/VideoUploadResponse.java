package com.example.strangerDrug.videos.dto;

import com.example.strangerDrug.videos.model.Video;

public class VideoUploadResponse {
    private String id;
    private String title;
    private String fileName;
    private String previewFileName;
    private long size;
    private String status;
    private String videoUrl;
    private String previewUrl;
    private String uploadedAt;

    public VideoUploadResponse() {

    }

    public VideoUploadResponse(Video video, String videoUrl, String previewUrl) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.fileName = video.getFileName();
        this.previewFileName = video.getPreviewFileName();
        this.size = video.getSize();
        this.status = video.getStatus().toString();
        this.videoUrl = videoUrl;
        this.previewUrl = previewUrl;
        this.uploadedAt = video.getUploadedAt().toString();
    }

    // Getters and Setters
    public String getId() { 
        return id; 
    }

    public void setId(String id) { 
        this.id = id; 
    }

    public String getTitle() { 
        return title; 
    }

    public void setTitle(String title) { 
        this.title = title; 
    }

    public String getFileName() { 
        return fileName; 
    }

    public void setFileName(String fileName) { 
        this.fileName = fileName; 
    }

    public String getPreviewFileName() { 
        return previewFileName; 
    }

    public void setPreviewFileName(String previewFileName) { 
        this.previewFileName = previewFileName; 
    }

    public long getSize() { 
        return size; 
    }

    public void setSize(long size) { 
        this.size = size; 
    }

    public String getStatus() { 
        return status; 
    }

    public void setStatus(String status) { 
        this.status = status; 
    }

    public String getVideoUrl() { 
        return videoUrl; 
    }

    public void setVideoUrl(String videoUrl) { 
        this.videoUrl = videoUrl; 
    }

    public String getPreviewUrl() { 
        return previewUrl; 
    }

    public void setPreviewUrl(String previewUrl) { 
        this.previewUrl = previewUrl; 
    }

    public String getUploadedAt() { 
        return uploadedAt; 
    }

    public void setUploadedAt(String uploadedAt) { 
        this.uploadedAt = uploadedAt; 
    }
}
