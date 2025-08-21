package com.example.strangerDrug.registro.service;

import com.example.strangerDrug.registro.model.Customer;
import com.example.strangerDrug.registro.model.User;

public interface IRegisterService {
    User saveCustomer(Customer customer);
}