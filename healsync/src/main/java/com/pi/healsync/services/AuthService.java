package com.pi.healsync.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.PasswordInvalid;
import com.pi.healsync.models.Hospital;
import com.pi.healsync.repositories.HospitalRepository;
import com.pi.healsync.security.JwtUtil;

@Service
public class AuthService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HospitalRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public String authenticateUser(String email, String senha){

        Optional<Hospital> hospitalOptional = repository.findByEmail(email);

        if (!hospitalOptional.isPresent()) {
            throw new NoSuchException("Hospital");
        }

        Hospital hospital = hospitalOptional.get();

        if (!passwordEncoder.matches(senha, hospital.getPassword())) {
            throw new PasswordInvalid();
        }

        String token = jwtUtil.generateToken(email, hospital.getId());

        return token;
    }
}
