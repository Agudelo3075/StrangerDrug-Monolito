package com.example.strangerDrug.termsAndConditionsApi.domain.port.in;

import java.util.List;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.Accepted;


public interface IAcceptAllTerms {
    List<Accepted> acceptAll(Long userId, String userEmail, List<Long> termIds, String ipAddress);
}