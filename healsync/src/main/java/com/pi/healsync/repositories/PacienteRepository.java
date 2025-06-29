package com.pi.healsync.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.healsync.models.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, UUID>{
    public Paciente findByCpf(String cpf);
}
