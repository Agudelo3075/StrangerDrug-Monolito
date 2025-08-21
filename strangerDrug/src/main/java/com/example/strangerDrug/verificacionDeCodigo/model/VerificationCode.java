package com.example.strangerDrug.verificacionDeCodigo.model;

import java.time.LocalDateTime;

public class VerificationCode {
    private Long id;
    private String email;
    private String code;
    private LocalDateTime generatedAt;
    private boolean used;

    public VerificationCode(Long id, String email, String code, LocalDateTime generatedAt, boolean used){
        this.id = id;
        this.email = email;
        this.code = code;
        this.generatedAt = generatedAt;
        this.used = used;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
