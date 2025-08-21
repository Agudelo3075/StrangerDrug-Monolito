package com.example.strangerDrug.registro.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.strangerDrug.registro.model.Customer;
import com.example.strangerDrug.registro.model.User;
import com.example.strangerDrug.registro.service.IRegisterService;
import com.example.strangerDrug.termsAndConditionsApi.application.service.TermsService;
import com.example.strangerDrug.termsAndConditionsApi.web.dto.TermResponse;
import com.example.strangerDrug.termsAndConditionsApi.web.webMapper.TermWebMapper;
import com.example.strangerDrug.verificacionDeCodigo.service.ICodeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class RegisterController {
    private final IRegisterService registerService;
    private final TermsService termsService;
    private final ICodeService codeService;
    private final TermWebMapper termWebMapper;
    

    @PostMapping("/customer")
    public ResponseEntity<User> saveCustomer(@Valid @RequestBody Customer customer, HttpServletRequest httpRequest){
        String ipAddress = httpRequest.getRemoteAddr();
        //validar verificacion de email
        codeService.checkStatus(customer.getEmail());
        User saved = registerService.saveCustomer(customer);
        termsService.acceptAll(saved.getId(), saved.getEmail(), getActiveTermIds(), ipAddress);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

     public List<Long> getActiveTermIds() {
        
        List<TermResponse> terms = termsService.getAllActiveTerms().stream().map(termWebMapper::toResponse).collect(Collectors.toList());
        if (terms != null && !terms.isEmpty()) {
            return terms.stream().map(TermResponse::getId).collect(Collectors.toList());
        }
        return List.of(1L);
    }
    
}