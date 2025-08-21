package com.example.strangerDrug.registro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.strangerDrug.registro.model.User;

@Repository
public interface RegisterRepository extends JpaRepository<User, Long>{
    
}