package com.pi.healsync.services;

import java.util.List;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.models.SalaDeEspera;
import com.pi.healsync.repositories.SalaDeEsperaRepository;

@Service
public class SalaDeEsperaService {

    private final SalaDeEsperaRepository repository;

    public SalaDeEsperaService(SalaDeEsperaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public SalaDeEspera insert(SalaDeEspera sala) {
        try {
            return repository.save(sala);
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao criar Sala de Espera");

        }
    }

    @Transactional(readOnly = true)
    public SalaDeEspera findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchException("Sala de Espera com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<SalaDeEspera> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public SalaDeEspera findBySala(String nomeSala) {
        SalaDeEspera sala = repository.findBySala(nomeSala);

        if (sala == null) {
            throw new NoSuchException("Sala de Espera com nome: " + nomeSala);
        }

        return sala;
    }
}
