package com.pi.healsync.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.PasswordInvalid;
import com.pi.healsync.models.Funcionario;
import com.pi.healsync.repositories.FuncionarioRepository;
import com.pi.healsync.security.JwtUtil;

@Service
public class AuthService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public String authenticateUser(String email, String senha){

        Optional<Funcionario> funcionarioOptional = repository.findByEmail(email);

        if (!funcionarioOptional.isPresent()) {
            throw new NoSuchException("funcionario");
        }

        Funcionario funcionario = funcionarioOptional.get();

        if (!passwordEncoder.matches(senha, funcionario.getSenha())) {
            throw new PasswordInvalid();
        }

        String token = jwtUtil.generateToken(email, funcionario.getId(), funcionario.getHospital().getId());

        return token;
    }
}
