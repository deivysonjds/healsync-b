package main.java.com.pi.healsync.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.ObjectNotCreated;
import com.pi.healsync.models.SalaDeEspera;
import com.pi.healsync.repositories.SalaDeEsperaRepository;

@Service
public class SalaDeEsperaService {

    @Autowired
    private SalaDeEsperaRepository repository;

    @Transactional
    public SalaDeEspera insert(SalaDeEspera sala) {
        SalaDeEspera novaSala;

        try {
            novaSala = repository.save(sala);
        } catch (Exception e) {
            throw new ObjectNotCreated(e);
        }

        return novaSala;
    }

    @Transactional
    public SalaDeEspera findById(UUID id) {
        Optional<SalaDeEspera> sala = repository.findById(id);

        if (!sala.isPresent()) {
            throw new NoSuchException("Sala de Espera");
        }

        return sala.get();
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
