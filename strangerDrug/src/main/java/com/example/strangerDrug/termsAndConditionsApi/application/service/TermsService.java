package com.example.strangerDrug.termsAndConditionsApi.application.service;

import java.util.List;
import java.util.Optional;

import com.example.strangerDrug.termsAndConditionsApi.domain.model.Accepted;
import com.example.strangerDrug.termsAndConditionsApi.domain.model.TermAndCondition;
import com.example.strangerDrug.termsAndConditionsApi.domain.port.in.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TermsService implements IAcceptTerm, IAcceptAllTerms, IGetAllActiveTerms, IGetActiveTermByType, IGetTermById, IGetAllTerms, IVerifyTermAcceptance, IVerifyAllTermsAccepted{

    private final IAcceptTerm acceptTerm;
    private final IAcceptAllTerms acceptAllTerms;
    private final IGetAllActiveTerms getAllActiveTerms;
    private final IGetActiveTermByType getActiveTermByType;
    private final IGetTermById getTermById;
    private final IGetAllTerms getAllTerms;
    private final IVerifyTermAcceptance verifyTermAcceptance;
    private final IVerifyAllTermsAccepted verifyAllTermsAccepted;

    @Override
    public boolean hasAcceptedTerm(Long userId, Long termId) {
        try {
          
            
            boolean hasAccepted = verifyTermAcceptance.hasAcceptedTerm(userId, termId);
            
          
            
            return hasAccepted;
            
        } catch (Exception e) {
           
            throw e;
        }
    }

    @Override
    public List<TermAndCondition> getAllActiveTerms() {
        try {
            
            
            List<TermAndCondition> activeTerms = getAllActiveTerms.getAllActiveTerms();
            
          
            
            return activeTerms;
            
        } catch (Exception e) {
           
            throw e;
        }
    }

    @Override
    public Optional<TermAndCondition> getActiveTermByType(String type) {
        try {
           
            
            Optional<TermAndCondition> term = getActiveTermByType.getActiveTermByType(type);
            
        
            return term;
            
        } catch (Exception e) {
           
            throw e;
        }
    }

    @Override
    public Accepted acceptTerm(Long userId, String userEmail, Long termId, String ipAddress) {
        try {
          
            
            Accepted accepted = acceptTerm.acceptTerm(userId, userEmail, termId, ipAddress);
            
           
            
            return accepted;
            
        } catch (Exception e) {
           
            throw e;
        }
    }

    @Override
    public void verifyAllTermsAccepted(String userEmail) {
        try {
            
            
            verifyAllTermsAccepted.verifyAllTermsAccepted(userEmail);
            
          
            
        } catch (Exception e) {
           
            throw e;
        }
    }

    @Override
    public List<TermAndCondition> getAllTerms() {
        try {
           
            
            List<TermAndCondition> allTerms = getAllTerms.getAllTerms();
            
            
            
            return allTerms;
            
        } catch (Exception e) {
           
            throw e;
        }
    }

    @Override
    public Optional<TermAndCondition> getById(Long id) {
        try {
           
            
            Optional<TermAndCondition> term = getTermById.getById(id);
            
            
            return term;
            
        } catch (Exception e) {
           
            throw e;
        }
    }

    @Override
    public List<Accepted> acceptAll(Long userId, String userEmail, List<Long> termIds, String ipAddress) {
        try {
         
            
            List<Accepted> acceptedList = acceptAllTerms.acceptAll(userId, userEmail, termIds, ipAddress);
            
          
            return acceptedList;
            
        } catch (Exception e) {
         
            throw e;
        }
    }
    
}