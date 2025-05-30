package com.pi.healsync.services;
import org.springframework.stereotype.Service;

import com.pi.healsync.repositories.FuncionarioRepository;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.ObjectNotCreated;
import com.pi.healsync.models.Funcionario;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    public Funcionario findById(UUID id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

        if (!funcionario.isPresent()) {
            throw new NoSuchException("Funcionario");
        }

        return funcionario.get();
    }

    public Funcionario insert(Funcionario funcionario) {
        Funcionario funcionarioRep;

        String senhaEncode = passwordEncoder.encode(funcionario.getSenha());
        funcionario.setSenha(senhaEncode);

        try {
            funcionarioRep = funcionarioRepository.save(funcionario);
        } catch (Exception e) {
            throw new ObjectNotCreated(e);
        }
        return funcionarioRep;
    }
    
}
