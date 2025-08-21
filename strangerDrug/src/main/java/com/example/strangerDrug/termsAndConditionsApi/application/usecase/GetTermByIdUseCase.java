package com.example.strangerDrug.termsAndConditionsApi.application.usecase;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.TermAndCondition;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.in.IGetTermById;
import lombok.RequiredArgsConstructor;

@Service
@Qualifier("getTermByIdUseCase")
@RequiredArgsConstructor
public class GetTermByIdUseCase implements IGetTermById{

    private final GetTermUseCase getTermUseCase;


    @Override
    @Transactional(readOnly = true)
    public Optional<TermAndCondition> getById(Long id) {
        try {
            
            
            Optional<TermAndCondition> term = Optional.of(getTermUseCase.getTerm(id));
            

            
            return term;
            
        } catch (Exception e) {
           
            throw e;
        }
    }

}