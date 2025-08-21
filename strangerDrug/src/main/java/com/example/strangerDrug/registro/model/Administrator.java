package com.example.strangerDrug.registro.model;

import java.time.LocalDateTime;

public class Administrator extends User{
    private String code;

    public Administrator(Long id, String name, String email, String password, String phone, String code,UserRole rol,
            LocalDateTime lastLogin, boolean verifiedCode, boolean verifiedTerm) {
        super(id, name, email, password, phone, rol, lastLogin, verifiedCode, verifiedTerm);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
