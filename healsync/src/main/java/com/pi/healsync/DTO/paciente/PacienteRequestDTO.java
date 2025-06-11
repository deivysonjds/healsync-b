package com.pi.healsync.DTO.paciente;

import com.pi.healsync.DTO.endereco.EnderecoRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequestDTO {
    private String name;
    private String email;
    private int age;
    private String cpf;
    private String telefone;
    private String rg;
    private String cns;
    private EnderecoRequestDTO endereco;
}
