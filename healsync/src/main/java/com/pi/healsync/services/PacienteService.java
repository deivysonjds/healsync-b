package com.pi.healsync.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.ObjectNotCreated;
import com.pi.healsync.models.Paciente;
import com.pi.healsync.repositories.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public Paciente insert(Paciente paciente) {
        try {
            return pacienteRepository.save(paciente);
        } catch (Exception e) {
            throw new ObjectNotCreated(e);
        }
    }

    @Transactional(readOnly = true)
    public Paciente findById(UUID id) {
       Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (!paciente.isPresent()) {
            throw new NoSuchException("paciente");
        }
        return paciente.get();
    }

    @Transactional(readOnly = true)
    public List<Paciente> findAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        if (pacientes.isEmpty()) {
            throw new NoSuchException("pacientes");
        }
        return pacientes;
    }

    @Transactional
    public void deleteById(UUID id) {
        if (!pacienteRepository.existsById(id)) {
            throw new NoSuchException("paciente");
        }
        pacienteRepository.deleteById(id);
    }
}
