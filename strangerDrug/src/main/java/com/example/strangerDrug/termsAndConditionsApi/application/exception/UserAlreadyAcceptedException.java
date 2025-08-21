package com.example.strangerDrug.termsAndConditionsApi.application.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyAcceptedException extends RuntimeException{

    
    public UserAlreadyAcceptedException(){
        super("User has already accepted this term");
       
    }
}