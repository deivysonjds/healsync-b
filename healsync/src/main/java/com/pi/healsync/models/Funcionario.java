package com.pi.healsync.models;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.pi.healsync.DTO.funcionario.FuncionarioRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario extends Usuario {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles role;
    @Column(nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "unidade_id", nullable = true)
    @Column(nullable = true)
    private Unidade unidade;

    public Funcionario(FuncionarioRequestDTO funcionarioRequestDTO) {
        super(funcionarioRequestDTO.getName(), funcionarioRequestDTO.getEmail(), funcionarioRequestDTO.getCpf(),
              funcionarioRequestDTO.getEndereco(), funcionarioRequestDTO.getTelefone(), funcionarioRequestDTO.getRg());
        this.role = Roles.valueOf(funcionarioRequestDTO.getRole().toUpperCase());
        this.senha = funcionarioRequestDTO.getSenha();
    }
    
}