package com.example.strangerDrug.verificacionDeCodigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.strangerDrug.verificacionDeCodigo.model.VerificationCode;

@Repository
public interface CodeRepository extends JpaRepository<VerificationCode, Long>{
    
}