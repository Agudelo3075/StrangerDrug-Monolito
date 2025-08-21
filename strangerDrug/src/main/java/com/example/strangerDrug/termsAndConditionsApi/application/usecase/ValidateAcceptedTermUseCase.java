package com.example.strangerDrug.termsAndConditionsApi.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.strangerDrug.termsAndConditionsApi.application.exception.UserAlreadyAcceptedException;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.out.ITermAcceptanceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateAcceptedTermUseCase {

    private final ITermAcceptanceRepository termAcceptanceRepository;

    

    @Transactional(readOnly = true)
    public void existsByUserIdAndTermId(Long userId, Long termId){
        try {
           
      
        
            if(termAcceptanceRepository.existsByUserIdAndTermId(userId, termId)){
               
                throw new UserAlreadyAcceptedException();
            }
            
          
            
        } catch (UserAlreadyAcceptedException e) {
           
            throw e;
        } catch (Exception e) {
            
            throw e;
        }
    }
}