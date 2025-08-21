package com.example.strangerDrug.termsAndConditionsApi.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.strangerDrug.termsAndConditionsApi.application.exception.TermNotActiveOrNotFoundException;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.out.ITermRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateExistTermUseCase {
    private final ITermRepository termRepository;

    @Transactional(readOnly = true)
    public void validateTerm(Long termId){
        try {
          
            
            termRepository.findById(termId)
                .filter(term -> term.isActive())
                .orElseThrow(() -> new TermNotActiveOrNotFoundException());
                
        } catch (TermNotActiveOrNotFoundException e) {
         
            throw e;
        } catch (Exception e) {
          
            throw e;
        }
    }
}