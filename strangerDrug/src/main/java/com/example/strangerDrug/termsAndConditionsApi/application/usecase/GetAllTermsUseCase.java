package com.example.strangerDrug.termsAndConditionsApi.application.usecase;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.TermAndCondition;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.in.IGetAllTerms;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.out.ITermRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetAllTermsUseCase implements IGetAllTerms{

    private final ITermRepository termRepository;


    @Override
    @Transactional(readOnly = true)
    public List<TermAndCondition> getAllTerms() {
        try {
        
            
            List<TermAndCondition> allTerms = termRepository.findAll();
            
            
            
            return allTerms;
            
        } catch (Exception e) {
           
            throw e;
        }
    }

}