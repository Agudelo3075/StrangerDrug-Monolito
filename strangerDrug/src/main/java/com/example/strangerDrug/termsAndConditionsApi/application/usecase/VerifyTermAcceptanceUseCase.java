package com.example.strangerDrug.termsAndConditionsApi.application.usecase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.in.IVerifyTermAcceptance;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.out.ITermAcceptanceRepository;
import lombok.RequiredArgsConstructor;

@Service
@Qualifier("verifyTermAcceptanceUseCase")
@RequiredArgsConstructor
public class VerifyTermAcceptanceUseCase implements IVerifyTermAcceptance{

    private final ITermAcceptanceRepository termAcceptanceRepository;
    private final ValidateExistTermUseCase validateExistTermUseCase;

    @Override
    @Transactional(readOnly = true)
    public boolean hasAcceptedTerm(Long userId, Long termId) {
        try {

         

            validateExistTermUseCase.validateTerm(termId);

          
            
            boolean hasAccepted = termAcceptanceRepository.existsByUserIdAndTermId(userId, termId);
            
           
            
            return hasAccepted;
            
        } catch (Exception e) {
           
            throw e;
        }
    }

}