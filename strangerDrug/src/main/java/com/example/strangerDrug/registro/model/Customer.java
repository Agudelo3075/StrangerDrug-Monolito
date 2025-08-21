package com.example.strangerDrug.registro.model;

import java.time.LocalDate;

public class Customer extends User{
    private LocalDate birthDate;

    public Customer(Long id, String name, String email, String password, String phone, LocalDate birthDate, boolean verifiedCode, boolean verifiedTerm){
        super(id, name, email, password, phone, UserRole.CUSTOMER, null, verifiedCode, verifiedTerm);
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    //Implementar lo de la edad
}