package com.pi.healsync.services;
import org.springframework.stereotype.Service;

import com.pi.healsync.models.Hospital;
import com.pi.healsync.repositories.FuncionarioRepository;

import main.java.com.pi.healsync.models.Funcionario;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Funcionario findByEmail(String email) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(email);

        if (!funcionario.isPresent()) {
            throw new NoSuchException("Funcionario");
        }

        return funcionario.get();
    }

    public Funcionario FindById(UUID id) {
        Optional<Funcionario> funcionario = repository.findById(id);

        if (!funcionario.isPresent()) {
            throw new NoSuchException("Funcionario");
        }

        return funcionario.get();
    }

    public Funcionario insert(Funcionario funcionario) {
        Funcionario funcionarioRep;

        String senhaEncode = passwordEncoder.encode(funcionario.getPassword());
        funcionarioRep.setPassword(senhaEncode);

        try {
            funcionarioRep = repository.save(funcionario);
        } catch (Exception e) {
            throw new ObjectNotCreated(e);
        }
        return funcionarioRep;
    }
    
}
