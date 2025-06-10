package com.pi.healsync.repositories;

import com.pi.healsync.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, UUID> {
    public List<Monitor> findAllByAtendimentoId(UUID atendimentoId);
}
