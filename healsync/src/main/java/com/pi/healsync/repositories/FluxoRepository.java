package com.pi.healsync.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.healsync.models.Fluxo;
import com.pi.healsync.models.Unidade;

@Repository

public interface FluxoRepository extends JpaRepository<Fluxo, UUID> {
    public List<Fluxo> findAllByUnidade(Unidade unidade);
}
