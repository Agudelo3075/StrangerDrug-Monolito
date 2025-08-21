package com.example.strangerDrug.termsAndConditionsApi.application.usecase;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.strangerDrug.termsAndConditionsApi.application.exception.TermsNotAcceptedException;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.TermAndCondition;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.in.IVerifyAllTermsAccepted;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.out.ITermAcceptanceRepository;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.out.ITermRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerifyAllTermsAcceptedUseCase implements IVerifyAllTermsAccepted{
    
    private final ITermAcceptanceRepository termAcceptanceRepository;
    private final ITermRepository termRepository;


    @Override
    @Transactional(readOnly = true)
    public void verifyAllTermsAccepted(String userEmail) {
        try {
          
            
            // Obtener todos los términos activos
           
            List<TermAndCondition> activeTerms = termRepository.findAllActiveTerms();
            
            
            // Obtener el ID del usuario por email
        
            Long userId = termAcceptanceRepository.getUserIdByEmail(userEmail)
                .orElseThrow(() -> new TermsNotAcceptedException("Usuario no encontrado"));
          
            // Verificar que el usuario ha aceptado todos los términos
          
            for (TermAndCondition term : activeTerms) {
                if (!termAcceptanceRepository.existsByUserIdAndTermId(userId, term.getId())) {
                   
                    throw new TermsNotAcceptedException("Debes aceptar todos los términos y condiciones");
                }
              
            }
            

            
        } catch (TermsNotAcceptedException e) {
           
            throw e;
        } catch (Exception e) {
         
            throw e;
        }
    }
} 