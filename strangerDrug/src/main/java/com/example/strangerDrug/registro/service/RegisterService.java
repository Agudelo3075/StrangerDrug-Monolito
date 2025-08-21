package com.example.strangerDrug.registro.service;

import org.springframework.stereotype.Service;
import com.example.strangerDrug.registro.model.Customer;
import com.example.strangerDrug.registro.model.User;
import com.example.strangerDrug.registro.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService implements IRegisterService{

    private final RegisterRepository registerRepository;

    @Override
    public User saveCustomer(Customer customer) {
        // validar unicidad del correo
        // validar edad
        // encriptar contraseña
        // guardar cliente
        throw new UnsupportedOperationException("Unimplemented method 'saveCustomer'");
    }
    
}