package com.example.strangerDrug.registro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.strangerDrug.registro.model.Customer;
import com.example.strangerDrug.registro.model.User;
import com.example.strangerDrug.registro.service.IRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class RegisterController {
    private final IRegisterService registerService;
    

    @PostMapping("/customer")
    public ResponseEntity<User> saveCustomer(@Valid @RequestBody Customer customer){
        User saved = registerService.saveCustomer(customer);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    
}