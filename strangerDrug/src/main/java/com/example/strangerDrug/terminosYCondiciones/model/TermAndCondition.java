package com.example.strangerDrug.terminosYCondiciones.model;

import java.time.LocalDateTime;

public class TermAndCondition {
    private Long id;
    private String title;
    private String content;
    private String version;
    private LocalDateTime creationTerm;
    private boolean active;
    private TermType type;

    public TermAndCondition(Long id, String title, String content, String version, LocalDateTime creationTerm, boolean active, TermType type) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.version = version;
        this.creationTerm = creationTerm;
        this.active = active;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    public LocalDateTime getCreationTerm() {
        return creationTerm;
    }

    public void setCreationTerm(LocalDateTime creationTerm) {
        this.creationTerm = creationTerm;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TermType getType() {
        return type;
    }

    public void setType(TermType type) {
        this.type = type;
    }
    
    public enum TermType{
        /* 
        PRIVACY_POLICY("Privacy Policy"),
        TERMS_AND_CONDITIONS("Terms and Conditions");

        private String type;

        TermType(String type){
            this.type = type;
        }

        public String getType(){
            return type;
        }
        */
    }
}
