package com.pi.healsync.services;

import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.ObjectNotCreated;
import com.pi.healsync.models.Endereco;
import com.pi.healsync.repositories.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public Endereco insert(Endereco endereco){
        
        try {
            return enderecoRepository.save(endereco);
        } catch (Exception e) {
            throw new ObjectNotCreated(e);
        }
    }

    @Transactional
    public Endereco findById(UUID id){
        Optional<Endereco> endereco = enderecoRepository.findById(id);

        return endereco.orElseThrow(() -> new NoSuchException("Endereco"));
    }
}