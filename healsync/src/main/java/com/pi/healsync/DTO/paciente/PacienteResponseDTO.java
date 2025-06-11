package com.pi.healsync.DTO.paciente;

import java.util.UUID;

import com.pi.healsync.DTO.endereco.EnderecoResponseDTO;
import com.pi.healsync.models.Paciente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private int age;
    private String cpf;
    private EnderecoResponseDTO endereco;
    private String senha;
    private String telefone;
    private String rg;
    private String cns;

    public PacienteResponseDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.name = paciente.getName();
        this.email = paciente.getEmail();
        this.cpf = paciente.getCpf();
        this.telefone = paciente.getTelefone();
        this.rg = paciente.getRg();
        this.cns = paciente.getCns();
        this.age = paciente.getAge();
    }
}
