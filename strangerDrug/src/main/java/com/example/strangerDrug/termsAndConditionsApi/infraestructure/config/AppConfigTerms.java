package com.example.strangerDrug.termsAndConditionsApi.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.strangerDrug.termsAndConditionsApi.application.service.TermsService;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.in.*;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.in.IAcceptTerm;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.out.ITermAcceptanceRepository;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.out.ITermRepository;
import com.example.strangerDrug.termsAndConditionsApi.infraestructure.persistance.adapter.AdapterAccepted;
import com.example.strangerDrug.termsAndConditionsApi.infraestructure.persistance.adapter.AdapterTerm;

@Configuration
public class AppConfigTerms {

    @Bean
    public ITermAcceptanceRepository termAcceptanceRepository(AdapterAccepted adapterAccepted){
        return adapterAccepted;
    }

    @Bean
    public ITermRepository termRepository(AdapterTerm adapterTerm){
        return adapterTerm;
    }

    @Bean
    public TermsService termsService(
            IAcceptTerm acceptTerm, IAcceptAllTerms acceptAllTerms, 
            IGetAllActiveTerms getAllActiveTerms, IGetActiveTermByType getActiveTermByType, 
            IGetTermById getTermById, IGetAllTerms getAllTerms, IVerifyTermAcceptance verifyTermAcceptance, 
            IVerifyAllTermsAccepted verifyAllTermsAccepted
        ){

        return new TermsService(
            acceptTerm, acceptAllTerms, getAllActiveTerms, 
            getActiveTermByType, getTermById, getAllTerms, 
            verifyTermAcceptance, verifyAllTermsAccepted
        );
    }
}
