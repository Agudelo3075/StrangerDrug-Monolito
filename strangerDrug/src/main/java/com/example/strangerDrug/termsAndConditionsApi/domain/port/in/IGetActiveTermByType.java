package com.example.strangerDrug.termsAndConditionsApi.domain.port.in;

import java.util.Optional;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.TermAndCondition;

public interface IGetActiveTermByType {
    Optional<TermAndCondition> getActiveTermByType(String type);
}