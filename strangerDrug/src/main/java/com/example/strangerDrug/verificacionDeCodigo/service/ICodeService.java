package com.example.strangerDrug.verificacionDeCodigo.service;

public interface ICodeService {
    void sendCode(String email);
    void checkStatus(String email);
    boolean verifyCode(String email, String code);
}