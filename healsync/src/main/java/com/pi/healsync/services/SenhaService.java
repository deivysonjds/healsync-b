package com.pi.healsync.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.models.Paciente;
import com.pi.healsync.models.Senha;
import com.pi.healsync.repositories.SenhaRepository;

@Service
public class SenhaService {
    @Autowired
    private SenhaRepository senhaRepository;

    @Transactional(readOnly = true)
    public Paciente findPacienteBySenha(String senha) {
        Senha senhaObj;
        try {
            senhaObj = senhaRepository.findBySenha(senha);
            return senhaObj.getPaciente();
        } catch (Exception e) {
            throw new NoSuchException("senha");
        }

    }

    @Transactional
    public Senha insert(Senha senha){
        return senhaRepository.save(senha);
    }
}
