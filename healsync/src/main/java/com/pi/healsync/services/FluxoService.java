package com.pi.healsync.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.ObjectNotCreated;
import com.pi.healsync.models.Fluxo;
import com.pi.healsync.repositories.FluxoRepository;

@Service
public class FluxoService {
    @Autowired
    private FluxoRepository repository;
    @Transactional
    public Fluxo insert(Fluxo fluxo) {
        try {
           return repository.save(fluxo);
        } catch (Exception e) {
           throw new ObjectNotCreated(e);
        }
    }
    @Transactional (readOnly = true)
    public Fluxo findById (UUID id) {
        Optional <Fluxo> optFluxo = repository.findById(id);
        if (!optFluxo.isPresent()) {
            throw new NoSuchException("Fluxo");   
        }
        return optFluxo.get();
    }
    @Transactional (readOnly = true)
    public List <Fluxo> getAll() {
        return repository.findAll();
    }
}
