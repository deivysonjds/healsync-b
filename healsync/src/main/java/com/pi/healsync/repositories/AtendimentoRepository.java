package com.pi.healsync.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.healsync.models.Atendimento;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, UUID> {
    public List<Atendimento> findByFluxoId(UUID fluxoId);
}
