package com.example.strangerDrug.termsAndConditionsApi.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TermsNotAcceptedException extends RuntimeException {


    
    public TermsNotAcceptedException(String message) {
        super(message);

    }
} 