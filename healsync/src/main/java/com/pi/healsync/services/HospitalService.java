package com.pi.healsync.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi.healsync.DTO.HospitalResponseDTO;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.ObjectNotCreated;
import com.pi.healsync.models.Hospital;
import com.pi.healsync.repositories.HospitalRepository;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository repository;

    @Transactional
    public HospitalResponseDTO insert(Hospital hospital){

        Hospital hospitalRepository;

        try {
            hospitalRepository = repository.save(hospital);
        } catch (Exception e) {
            throw new ObjectNotCreated();
        }

        HospitalResponseDTO hospitaResponselDTO = new HospitalResponseDTO(hospitalRepository);
        return hospitaResponselDTO;
    }

    @Transactional
    public HospitalResponseDTO findByID(UUID id){
        Optional<Hospital> hospital = repository.findById(id);

        if (!hospital.isPresent()) {
            throw new NoSuchException("Hospital");
        }

        return new HospitalResponseDTO(hospital.get());
    }
}
