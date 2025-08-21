package com.example.strangerDrug.termsAndConditionsApi.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.Accepted;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.TermAndCondition;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.User;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.in.IAcceptTerm;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.out.ITermAcceptanceRepository;
import lombok.RequiredArgsConstructor;

@Service("AcceptTermUseCase")
@RequiredArgsConstructor
public class AcceptTermUseCase implements IAcceptTerm {

    private final ValidateExistTermUseCase validateExistTermUseCase;
    private final ValidateAcceptedTermUseCase validateAcceptedTermUseCase;
    private final ITermAcceptanceRepository termAcceptanceRepository;
    private final GetTermUseCase getTermUseCase;

    @Override
    @Transactional
    public Accepted acceptTerm(Long userId, String userEmail, Long termId, String ipAddress) {
        try {
  
            
            // Validar que el término existe y está activo
          
            validateExistTermUseCase.validateTerm(termId);
            
            
            // Validar que el usuario no ha aceptado este término antes
          
            validateAcceptedTermUseCase.existsByUserIdAndTermId(userId, termId);
           
            
            // Obtener el término

            TermAndCondition term = getTermUseCase.getTerm(termId);
          
                
            // Crear el usuario
           
            User user = new User(userId, userEmail);
            
            // Crear la aceptación con todos los datos requeridos
           
            Accepted accepted = new Accepted(user, term, ipAddress);
            
            // Guardar y retornar la aceptación
            
            Accepted savedAccepted = termAcceptanceRepository.save(accepted);
            
           
            
            return savedAccepted;
            
        } catch (Exception e) {
           
            throw e;
        }
    }
}