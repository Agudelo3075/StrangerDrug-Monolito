package com.example.strangerDrug.termsAndConditionsApi.infraestructure.persistance.adapter;

import java.util.Optional;
import org.springframework.stereotype.Component;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.Accepted;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.out.ITermAcceptanceRepository;
import com.example.strangerDrug.termsAndConditionsApi.infraestructure.persistance.entity.AcceptedEntity;
import com.example.strangerDrug.termsAndConditionsApi.infraestructure.persistance.mapper.AcceptedMapper;
import com.example.strangerDrug.termsAndConditionsApi.infraestructure.persistance.repository.ORMaccepted;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor
public class AdapterAccepted implements ITermAcceptanceRepository {

    private final ORMaccepted ormAccepted;
    private final AcceptedMapper acceptedMapper;


    @Override
    public Accepted save(Accepted acceptance) {
        try {
          
            
            AcceptedEntity acceptedEntity = acceptedMapper.toEntity(acceptance);
           
            
            AcceptedEntity savedAcceptedEntity = ormAccepted.save(acceptedEntity);
        
            Accepted savedAccepted = acceptedMapper.toDomain(savedAcceptedEntity);
          
            
            return savedAccepted;
            
        } catch (Exception e) {
         
            throw e;
        }
    }

    @Override
    public boolean existsByUserIdAndTermId(Long userId, Long termId) {
        try {
        
            
            boolean exists = ormAccepted.existsByUserIdAndTerminoAceptadoId(userId, termId);
            
        
            
            return exists;
            
        } catch (Exception e) {
           
            throw e;
        }
    }

    @Override
    public Optional<Long> getUserIdByEmail(String email) {
        try {
          
            
            Optional<Long> userId = ormAccepted.findFirstByUserEmail(email);
            
         
            
            return userId;
            
        } catch (Exception e) {
          
            throw e;
        }
    }
}