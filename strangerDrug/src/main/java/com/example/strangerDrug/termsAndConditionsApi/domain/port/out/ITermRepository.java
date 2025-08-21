package com.example.strangerDrug.termsAndConditionsApi.domain.port.out;

import java.util.List;
import java.util.Optional;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.TermAndCondition;

public interface ITermRepository {
    List<TermAndCondition> findAllActiveTerms();
    Optional<TermAndCondition> findActiveTermByType(String type);
    Optional<TermAndCondition> findById(Long id);
    List<TermAndCondition> findAll();
} 