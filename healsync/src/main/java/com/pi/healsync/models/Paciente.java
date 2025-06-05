package com.pi.healsync.models;


import com.pi.healsync.DTO.paciente.PacienteRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente extends Usuario {

    @Column(unique = true, nullable = false)
    private String cns;

    public Paciente(String name, String email, String cpf, String endereco, String telefone, String rg,
                    String cns) {
        super(name, email, cpf, endereco, telefone, rg);
        this.cns = cns;
    }

    public Paciente(PacienteRequestDTO pacienteRequestDTO) {
        super(pacienteRequestDTO.getName(), pacienteRequestDTO.getEmail(), pacienteRequestDTO.getCpf(),
              pacienteRequestDTO.getEndereco(), pacienteRequestDTO.getTelefone(), pacienteRequestDTO.getRg());
        this.cns = pacienteRequestDTO.getCns();
    }

}
