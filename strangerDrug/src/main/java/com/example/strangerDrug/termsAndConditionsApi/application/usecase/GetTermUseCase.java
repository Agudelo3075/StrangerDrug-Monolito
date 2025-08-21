package com.example.strangerDrug.termsAndConditionsApi.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.strangerDrug.termsAndConditionsApi.application.exception.TermNotActiveOrNotFoundException;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.TermAndCondition;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.out.ITermRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetTermUseCase {
    private final ITermRepository termRepository;

    @Transactional(readOnly = true)
    public TermAndCondition getTerm(Long termId){
        try {
           
            
            TermAndCondition term = termRepository.findById(termId)
                .orElseThrow(TermNotActiveOrNotFoundException::new);
            
           
            
            return term;
            
        } catch (TermNotActiveOrNotFoundException e) {
           
            throw e;
        } catch (Exception e) {
          
            throw e;
        }
    } 
}