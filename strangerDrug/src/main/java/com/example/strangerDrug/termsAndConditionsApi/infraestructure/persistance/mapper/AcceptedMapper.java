package com.example.strangerDrug.termsAndConditionsApi.infraestructure.persistance.mapper;

import org.springframework.stereotype.Component;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.Accepted;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.User;
import com.example.strangerDrug.termsAndConditionsApi.infraestructure.persistance.entity.AcceptedEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AcceptedMapper {
    
    private final TermMapper termMapper;

    
    public Accepted toDomain(AcceptedEntity entity) {
        try {
            if (entity == null) {
              
                return null;
            }
            
        
            
            User user = new User(entity.getUserId(), entity.getUserEmail());
            
            Accepted accepted = new Accepted(
                entity.getId(),
                user,
                entity.getFechaHoraDeAceptacion(),
                termMapper.toDomain(entity.getTerminoAceptado()),
                entity.getIp()
            );
            
           
            
            return accepted;
            
        } catch (Exception e) {
           
            throw e;
        }
    }

    public AcceptedEntity toEntity(Accepted domain) {
        try {
            if (domain == null) {
              
                return null;
            }
            
            
            AcceptedEntity entity = new AcceptedEntity();
            entity.setId(domain.getId());
            entity.setUserId(domain.getUser().getId());
            entity.setUserEmail(domain.getUser().getEmail());
            entity.setFechaHoraDeAceptacion(domain.getFechaHoraDeAceptacion());
            entity.setTerminoAceptado(termMapper.toEntity(domain.getTerminoAceptado()));
            entity.setIp(domain.getIp());
            
          
            
            return entity;
            
        } catch (Exception e) {
           
            throw e;
        }
    }
} 