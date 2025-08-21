package com.example.strangerDrug.termsAndConditionsApi.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.strangerDrug.termsAndConditionsApi.application.service.TermsService;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.Accepted;
import com.example.strangerDrug.termsAndConditionsApi.web.dto.AcceptedMultipleRequest;
import com.example.strangerDrug.termsAndConditionsApi.web.dto.AcceptedRequest;
import com.example.strangerDrug.termsAndConditionsApi.web.dto.ApiResponse;
import com.example.strangerDrug.termsAndConditionsApi.web.dto.TermResponse;
import com.example.strangerDrug.termsAndConditionsApi.web.webMapper.TermWebMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/terms")
@RequiredArgsConstructor
public class TermController {
    
    private final TermsService termsService;
    private final TermWebMapper termWebMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TermResponse>>> getAllActiveTerms() {
        List<TermResponse> terms = termsService.getAllActiveTerms().stream()
            .map(termWebMapper::toResponse)
            .collect(Collectors.toList());

        if(!terms.isEmpty()){
            return ResponseEntity.ok(ApiResponse.success("Active terms retrieved successfully", terms));
        }
        return ResponseEntity.noContent().build();
       
    }

    @GetMapping("/type")
    public ResponseEntity<ApiResponse<TermResponse>> getActiveTermByType(@RequestParam(required = true) String type) {
        return termsService.getActiveTermByType(type)
        .map(term -> ResponseEntity.ok(ApiResponse.success("Term retrieved successfully", termWebMapper.toResponse(term))))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiResponse.error("No active term found for type: " + type)));
    }

    @PostMapping("/accept")
    public ResponseEntity<ApiResponse<Accepted>> acceptTerm(
            @Valid @RequestBody AcceptedRequest request,
            HttpServletRequest httpRequest) {
        
        String ipAddress = httpRequest.getRemoteAddr();
        Accepted accepted = termsService.acceptTerm(request.getUserId(), request.getUserEmail(), request.getTermId(), ipAddress);
        
        return ResponseEntity.ok(ApiResponse.success("Terms accepted successfully", accepted));
    }
  
    @PostMapping("/accept/multiple")
    public ResponseEntity<ApiResponse<List<Accepted>>> acceptMultipleTerms(
            @Valid @RequestBody AcceptedMultipleRequest request,
            HttpServletRequest httpRequest) {

        String ipAddress = httpRequest.getRemoteAddr();
        List<Accepted> acceptedList = termsService.acceptAll(
            request.getUserId(), request.getUserEmail(), request.getTermIds(), ipAddress);

        return ResponseEntity.ok(ApiResponse.success("Términos aceptados correctamente", acceptedList));
    }

    @GetMapping("/verify")
    public ResponseEntity<ApiResponse<Boolean>> verifyAcceptance(
            @RequestParam(required = true) Long userId,
            @RequestParam(required = true) Long termId) {
        
        boolean hasAccepted = termsService.hasAcceptedTerm(userId, termId);
        return ResponseEntity.ok(ApiResponse.success("Verification completed", hasAccepted));
    }

    @GetMapping("/verify/email")
    public ResponseEntity<ApiResponse<Void>> verifyAllTermsByEmail(@RequestParam(required = true) String email) {
        termsService.verifyAllTermsAccepted(email);
        return ResponseEntity.ok(ApiResponse.success("Todos los términos han sido aceptados"));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<TermResponse>>> getAllTerms() {
        List<TermResponse> terms = termsService.getAllTerms().stream()
            .map(termWebMapper::toResponse)
            .collect(Collectors.toList());

        if(!terms.isEmpty()){
            return ResponseEntity.ok(ApiResponse.success("All terms retrieved successfully", terms));
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TermResponse> getTermById(@PathVariable Long id) {
        return termsService.getById(id)
            .map(term -> new ResponseEntity<>(termWebMapper.toResponse(term), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}