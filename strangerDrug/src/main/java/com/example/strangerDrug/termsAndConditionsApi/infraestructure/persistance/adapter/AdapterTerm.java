package com.example.strangerDrug.termsAndConditionsApi.infraestructure.persistance.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.TermAndCondition;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.out.ITermRepository;
import com.example.strangerDrug.termsAndConditionsApi.infraestructure.persistance.mapper.TermMapper;
import com.example.strangerDrug.termsAndConditionsApi.infraestructure.persistance.repository.ORMterm;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdapterTerm implements ITermRepository {

    private final ORMterm ormTerm;
    private final TermMapper termMapper;

    @Override
    public List<TermAndCondition> findAllActiveTerms() {
        try {
            
            List<TermAndCondition> activeTerms = ormTerm.findAllActiveTerms().stream()
                .map(termMapper::toDomain)
                .collect(Collectors.toList());
            
          
            
            return activeTerms;
            
        } catch (Exception e) {
            
            throw e;
        }
    }

    @Override
    public Optional<TermAndCondition> findActiveTermByType(String type) {
        try {
          
            Optional<TermAndCondition> term = ormTerm.findActiveTermByType(type)
                .map(termMapper::toDomain);
        
            return term;
            
        } catch (Exception e) {
           
            throw e;
        }
    }

    @Override
    public Optional<TermAndCondition> findById(Long id) {
        try {
            
            Optional<TermAndCondition> term = ormTerm.findById(id)
                .map(termMapper::toDomain);
            
           
            return term;
            
        } catch (Exception e) {
           
            throw e;
        }
    }

    @Override
    public List<TermAndCondition> findAll() {
        try {
            
            List<TermAndCondition> allTerms = ormTerm.findAll().stream()
                .map(termMapper::toDomain)
                .collect(Collectors.toList());
            
           
            return allTerms;
            
        } catch (Exception e) {
          
            throw e;
        }
    }
}