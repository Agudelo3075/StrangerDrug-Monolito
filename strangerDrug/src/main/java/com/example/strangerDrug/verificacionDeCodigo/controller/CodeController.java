package com.example.strangerDrug.verificacionDeCodigo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.strangerDrug.verificacionDeCodigo.controller.dto.ApiResponse;
import com.example.strangerDrug.verificacionDeCodigo.controller.dto.SendCodeRequest;
import com.example.strangerDrug.verificacionDeCodigo.controller.dto.VerifyCodeRequest;
import com.example.strangerDrug.verificacionDeCodigo.service.ICodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/code")
@RequiredArgsConstructor
public class CodeController {

    private final ICodeService codeService;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<Void>> sendCode(@Valid @RequestBody SendCodeRequest request){
        codeService.sendCode(request.getEmail());
        return ResponseEntity.ok(ApiResponse.success("Verification code sent successfully"));
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<Boolean>> verifyCode(@Valid @RequestBody VerifyCodeRequest request){
        boolean isValid = codeService.verifyCode(request.getEmail(), request.getCode());
        if(isValid){
            return ResponseEntity.ok(ApiResponse.success("Code verified successfully", true));
        }else{
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Invalid or expired verification code"));
        }
    }

    @PostMapping("/status")
    public ResponseEntity<ApiResponse<Void>> checkStatus(@Valid @RequestBody SendCodeRequest request){
        codeService.checkStatus(request.getEmail());
        return ResponseEntity.ok(ApiResponse.success("Email has been verified"));
    }

}