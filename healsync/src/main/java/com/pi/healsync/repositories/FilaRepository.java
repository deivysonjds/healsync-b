package com.pi.healsync.repositories;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.healsync.models.Fila;

@Repository
public interface FilaRepository extends JpaRepository<Fila, UUID> {

    public Fila findByDate(LocalDate date);

}
