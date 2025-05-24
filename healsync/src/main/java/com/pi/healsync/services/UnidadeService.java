package com.pi.healsync.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.pi.healsync.DTO.UnidadeRespondeDto;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.ObjectNotCreated;
import com.pi.healsync.models.Hospital;
import com.pi.healsync.models.Unidade;
import com.pi.healsync.repositories.HospitalRepository;
import com.pi.healsync.repositories.UnidadeRepository;

public class UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;
    @Autowired 
    private HospitalRepository hospitalRepository;

    public UnidadeRespondeDto insert(Unidade unidade, UUID hospital_id){

        Optional<Hospital> hospitalOptional = hospitalRepository.findById(hospital_id);
        
        if (!hospitalOptional.isPresent()) {
            throw new NoSuchException("Hospital");
        }

        unidade.setHospital(hospitalOptional.get());

        try {
            unidadeRepository.save(unidade);
        } catch (Exception e) {
            throw new ObjectNotCreated(e);
        }

        UnidadeRespondeDto dto = new UnidadeRespondeDto(unidade);

        return dto;
    }
}
