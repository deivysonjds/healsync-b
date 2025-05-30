package com.pi.healsync.DTO;
import lombok.NoArgsConstructor;

import java.util.UUID;

import com.pi.healsync.models.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private String cpf;
    private String endereco;
    private String telefone;
    private String rg;
    private String role;

    public FuncionarioResponseDTO(Funcionario funcionario) {
        id = funcionario.getId();
        name = funcionario.getName();
        email = funcionario.getEmail();
        cpf = funcionario.getCpf();
        endereco = funcionario.getEndereco();
        telefone = funcionario.getTelefone();
        rg = funcionario.getRg();
        role = funcionario.getRole();
    }
}
