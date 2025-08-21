package com.example.strangerDrug.verificacionDeCodigo.service;

import org.springframework.stereotype.Service;
import com.example.strangerDrug.verificacionDeCodigo.external.IMailService;
import com.example.strangerDrug.verificacionDeCodigo.repository.CodeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeService implements ICodeService{

    private final CodeRepository codeRepository;
    private final IMailService mailService;

    @Override
    public void sendCode(String email) {
        // invalidar codigos anteriores
        // generar y guardar nuevo codigo
        // enviar por mail el codigo usando mailservice
        throw new UnsupportedOperationException("Unimplemented method 'sendCode'");
    }

    @Override
    public void checkStatus(String email) {
        // verificar con metodo de repository
        throw new UnsupportedOperationException("Unimplemented method 'checkStatus'");
    }

    @Override
    public boolean verifyCode(String email, String code) {
        // verificar que el codigo existe
        // verificar que el codigo es valido
        // marcar como usado
        // marcar email como verificado
        throw new UnsupportedOperationException("Unimplemented method 'verifyCode'");
    }
    
}