package com.example.strangerDrug.termsAndConditionsApi.application.usecase;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.TermAndCondition;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.in.IGetAllActiveTerms;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.out.ITermRepository;
import lombok.RequiredArgsConstructor;

@Service
@Qualifier("getAllActiveTermsUseCase")
@RequiredArgsConstructor
public class GetAllActiveTermsUseCase implements IGetAllActiveTerms{

    private final ITermRepository termRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TermAndCondition> getAllActiveTerms() {
        try {    
            List<TermAndCondition> activeTerms = termRepository.findAllActiveTerms();
            
          
            
            return activeTerms;
            
        } catch (Exception e) {
           
            throw e;
        }
    }

}