package com.pi.healsync.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.healsync.models.Senha;


@Repository
public interface SenhaRepository extends JpaRepository<Senha, UUID>{
    Senha findBySenha(String senha);
}
