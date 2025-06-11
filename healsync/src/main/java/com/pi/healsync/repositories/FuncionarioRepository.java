package com.pi.healsync.repositories;
import com.pi.healsync.models.Funcionario;
import com.pi.healsync.models.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {
    Optional<Funcionario> findByEmail(String email);
    Optional<Funcionario> findByCpf(String cpf);
    Optional<Funcionario> findByRg(String rg);

    List<Funcionario> findByHospital(Hospital hospital);
}

