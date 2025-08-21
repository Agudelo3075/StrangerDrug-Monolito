package com.example.strangerDrug.termsAndConditionsApi.application.usecase;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.TermAndCondition;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.in.IGetActiveTermByType;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.out.ITermRepository;
import lombok.RequiredArgsConstructor;

@Service
@Qualifier("getActiveTermByTypeUseCase")
@RequiredArgsConstructor
public class GetActiveTermByTypeUseCase implements IGetActiveTermByType{

    private final ITermRepository termRepository;


    @Override
    @Transactional(readOnly = true)
    public Optional<TermAndCondition> getActiveTermByType(String type) {
        try {
         
            
            Optional<TermAndCondition> term = termRepository.findActiveTermByType(type);
            

            
            return term;
            
        } catch (Exception e) {
          
            throw e;
        }
    }
}