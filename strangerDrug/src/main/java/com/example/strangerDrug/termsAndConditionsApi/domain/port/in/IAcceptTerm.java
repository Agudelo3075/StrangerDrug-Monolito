package com.example.strangerDrug.termsAndConditionsApi.domain.port.in;

import com.example.strangerDrug.termsAndConditionsApi.domain.model.Accepted;

public interface IAcceptTerm {
    Accepted acceptTerm(Long userId, String userEmail, Long termId, String ipAddress);
} 