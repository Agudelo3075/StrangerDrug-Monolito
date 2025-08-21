package com.example.strangerDrug.termsAndConditionsApi.web.webMapper;

import org.springframework.stereotype.Component;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.TermAndCondition;
import com.example.strangerDrug.termsAndConditionsApi.web.dto.TermResponse;


@Component
public class TermWebMapper {
    
    public TermResponse toResponse(TermAndCondition term) {
        if (term == null) return null;
        
        return new TermResponse(
            term.getId(),
            term.getTitle(),
            term.getContent(),
            term.getVersion(),
            term.getCreateTerm(),
            term.isActive(),
            term.getType().name()
        );
    }
} 