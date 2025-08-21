package com.example.strangerDrug.termsAndConditionsApi.domain.port.out;

import java.util.Optional;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.Accepted;


public interface ITermAcceptanceRepository {
    Accepted save(Accepted acceptance);
    boolean existsByUserIdAndTermId(Long userId, Long termId);
    Optional<Long> getUserIdByEmail(String email);
} 