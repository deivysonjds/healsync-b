package com.pi.healsync.repositories;

import com.pi.healsync.models.SalaDeEspera;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaDeEsperaRepository extends JpaRepository<SalaDeEspera, UUID> {
    SalaDeEspera findBySala(String sala);
}
