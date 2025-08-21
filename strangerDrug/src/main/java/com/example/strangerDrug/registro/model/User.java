package com.example.strangerDrug.registro.model;

import java.time.LocalDateTime;

public class User {
    protected Long id;
    protected String name;
    protected String email;
    protected String password;
    protected String phone;
    protected UserRole rol;
    protected LocalDateTime lastLogin;
    protected boolean verifiedCode;
    protected boolean verifiedTerm;

    protected User(String name, String email, String password, String phone, UserRole rol){
        this(null, name, email, password, phone, rol, null, false, false);
    }

    protected User(Long id, String name, String email, String password, String phone, UserRole rol, LocalDateTime lastLogin, boolean verifiedCode, boolean verifiedTerm) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.rol = rol;
        this.lastLogin = lastLogin;
        this.verifiedCode = verifiedCode;
        this.verifiedTerm = verifiedTerm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserRole getRol() {
        return rol;
    }

    public void setRol(UserRole rol) {
        this.rol = rol;
    }
    
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isVerifiedCode() {
        return verifiedCode;
    }

    public void setVerifiedCode(boolean verifiedCode) {
        this.verifiedCode = verifiedCode;
    }
    
    public boolean isVerifiedTerm() {
        return verifiedTerm;
    } 

    public void setVerifiedTerm(boolean verifiedTerm) {
        this.verifiedTerm = verifiedTerm;
    }

    //Metodos para verificar el codigo y los terminos y condiciones
    public void verifyCode(){
        this.verifiedCode = true;
    }

    public void verifyTerm(){
        this.verifiedTerm = true;
    }

    public boolean isAdmin() {
        return rol == UserRole.ADMIN;
    }

    public boolean isCustomer() {
        return rol == UserRole.CUSTOMER;
    }

    public enum UserRole{
        ADMIN("Administrator"),
        CUSTOMER("Customer");

        private String role;

        UserRole(String role) {
            this.role = role;
        }
        
        public String getRole() {
            return role;
        }
    }
}