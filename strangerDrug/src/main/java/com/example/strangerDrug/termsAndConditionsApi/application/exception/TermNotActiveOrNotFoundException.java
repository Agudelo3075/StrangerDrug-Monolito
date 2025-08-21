package com.example.strangerDrug.termsAndConditionsApi.application.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TermNotActiveOrNotFoundException extends RuntimeException{


    public TermNotActiveOrNotFoundException(){
        super("Term not found or not active");

    }
}