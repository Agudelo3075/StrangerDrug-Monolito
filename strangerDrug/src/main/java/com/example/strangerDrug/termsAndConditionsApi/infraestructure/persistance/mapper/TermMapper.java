package com.example.strangerDrug.termsAndConditionsApi.infraestructure.persistance.mapper;

import org.springframework.stereotype.Component;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.TermAndCondition;
import com.example.strangerDrug.termsAndConditionsApi.infraestructure.persistance.entity.TermAndConditionEntity;

@Component
public class TermMapper {
    
    
    public TermAndCondition toDomain(TermAndConditionEntity entity) {
        try {
            if (entity == null) {
               
                return null;
            }
            
           
            
            TermAndCondition term = new TermAndCondition(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getVersion(),
                entity.getCreateTerm(),
                entity.isActive(),
                TermAndCondition.TermType.valueOf(entity.getType())
            );
            
            
            
            return term;
            
        } catch (Exception e) {
           
            throw e;
        }
    }

    public TermAndConditionEntity toEntity(TermAndCondition domain) {
        try {
            if (domain == null) {
               
                return null;
            }
            
          
            
            TermAndConditionEntity entity = new TermAndConditionEntity();
            entity.setId(domain.getId());
            entity.setTitle(domain.getTitle());
            entity.setContent(domain.getContent());
            entity.setVersion(domain.getVersion());
            entity.setCreateTerm(domain.getCreateTerm());
            entity.setActive(domain.isActive());
            entity.setType(domain.getType().name());
            
          
            
            return entity;
            
        } catch (Exception e) {
         
            throw e;
        }
    }
} 