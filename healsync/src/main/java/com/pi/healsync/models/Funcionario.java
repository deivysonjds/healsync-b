package com.pi.healsync.models;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.pi.healsync.DTO.FuncionarioRequestDTO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario extends Usuario {

    private String role;
    private String senha;

    public Funcionario(FuncionarioRequestDTO funcionarioRequestDTO) {
        super(funcionarioRequestDTO.getName(), funcionarioRequestDTO.getEmail(), funcionarioRequestDTO.getCpf(),
              funcionarioRequestDTO.getEndereco(), funcionarioRequestDTO.getTelefone(), funcionarioRequestDTO.getRg());
        this.role = funcionarioRequestDTO.getRole();
        this.senha = funcionarioRequestDTO.getSenha();
    }
    
}