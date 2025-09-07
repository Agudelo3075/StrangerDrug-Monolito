package com.example.strangerDrug.videos.model;

import java.time.LocalDateTime;

public class Video {
    private String id;
    private String title;
    private String description;
    private String fileName;
    private String previewFileName;
    private String contentType;
    private long size;
    private String s3Key;
    private String previewS3Key;
    private LocalDateTime uploadedAt;
    private VideoStatus status;


    //Constructors
    public Video() {

    }

    public Video(String id, String title, String description, String fileName, String contentType, 
                long size, String s3key){
            
        this.id = id;
        this.title = title;
        this.description = description;
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
        this.s3Key = s3key;
        this.uploadedAt = LocalDateTime.now();
        this.status = VideoStatus.UPLOADING;
    }

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
    
    public String getDescription() { 
        return description; 
    }

    public void setDescription(String description) { 
        this.description = description; 
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

    public String getContentType() { 
        return contentType; 
    }

    public void setContentType(String contentType) { 
        this.contentType = contentType; 
    }

    public long getSize() { 
        return size; 
    }

    public void setSize(long size) { 
        this.size = size; 
    }

    public String getS3Key() { 
        return s3Key; 
    }

    public void setS3Key(String s3Key) { 
        this.s3Key = s3Key; 
    }

    public String getPreviewS3Key() { 
        return previewS3Key; 
    }

    public void setPreviewS3Key(String previewS3Key) { 
        this.previewS3Key = previewS3Key; 
    }

    public LocalDateTime getUploadedAt() { 
        return uploadedAt; 
    }

    public void setUploadedAt(LocalDateTime uploadedAt) { 
        this.uploadedAt = uploadedAt; 
    }

    public VideoStatus getStatus() { 
        return status; 
    }

    public void setStatus(VideoStatus status) { 
        this.status = status; 
    }
    
    public enum VideoStatus {
        UPLOADING,
        PROCESSING,
        READY,
        ERROR
    }
}
