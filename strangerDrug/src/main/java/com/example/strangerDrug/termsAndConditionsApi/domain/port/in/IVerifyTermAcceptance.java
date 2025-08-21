package com.example.strangerDrug.termsAndConditionsApi.domain.port.in;

public interface IVerifyTermAcceptance {
    boolean hasAcceptedTerm(Long userId, Long termId);
}