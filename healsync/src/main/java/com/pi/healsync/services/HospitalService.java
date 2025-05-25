package com.pi.healsync.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.ObjectNotCreated;
import com.pi.healsync.models.Hospital;
import com.pi.healsync.repositories.HospitalRepository;

@Service
public class HospitalService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HospitalRepository repository;

    @Transactional
    public Hospital insert(Hospital hospital){

        Hospital hospitalRep;

        String senhaEncode = passwordEncoder.encode(hospital.getPassword());
        hospital.setPassword(senhaEncode);

        try {
            hospitalRep = repository.save(hospital);
        } catch (Exception e) {
            throw new ObjectNotCreated(e);
        }
        return hospitalRep;
    }

    @Transactional
    public Hospital findByID(UUID id){
        Optional<Hospital> hospital = repository.findById(id);

        if (!hospital.isPresent()) {
            throw new NoSuchException("Hospital");
        }

        return hospital.get();
    }
}
