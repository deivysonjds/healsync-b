package com.pi.healsync.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.ObjectNotCreated;
import com.pi.healsync.models.Atendimento;
import com.pi.healsync.repositories.AtendimentoRepository;

@Service
public class AtendimentoService {
    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Transactional
    public Atendimento insert(Atendimento atendimento) {
        try {
            return atendimentoRepository.save(atendimento);
        } catch (Exception e) {
            throw new ObjectNotCreated(e);
        }
    }

    @Transactional(readOnly = true)
    public Atendimento findById(UUID id) {
        Optional<Atendimento> atendimento = atendimentoRepository.findById(id);
        if (atendimento.isPresent()) {
            return atendimento.get();
        } else {
            throw new NoSuchException("atendiemnto");
        }
    }

    @Transactional(readOnly = true)
    public List<Atendimento> findAll() {
        return atendimentoRepository.findAll();
    }

    @Transactional
    public void deleteById(UUID id) {
        if (!atendimentoRepository.existsById(id)) {
            throw new NoSuchException("atendimento");
        }
        atendimentoRepository.deleteById(id);
    }


}
