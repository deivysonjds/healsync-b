package com.pi.healsync.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.healsync.models.Hospital;
import com.pi.healsync.models.Unidade;
import java.util.List;


@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, UUID> {
    List<Unidade> findByHospital(Hospital hospital);
}
