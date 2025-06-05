package com.pi.healsync.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.healsync.models.Fluxo;

@Repository

public interface FluxoRepository extends JpaRepository<Fluxo, UUID> {

}
