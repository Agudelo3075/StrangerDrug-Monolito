package com.example.strangerDrug.termsAndConditionsApi.domain.port.in;

import java.util.List;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.TermAndCondition;

public interface IGetAllTerms {
    List<TermAndCondition> getAllTerms();
}