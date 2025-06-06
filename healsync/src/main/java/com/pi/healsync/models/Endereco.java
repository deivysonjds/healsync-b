package com.pi.healsync.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import com.pi.healsync.DTO.endereco.EnderecoRequestDTO;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String rua;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private int numero;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String cep;
    private String complemento;
    @Column(nullable = false)
    private String uf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unidade_id", nullable = true)
    private Unidade unidade;

    public Endereco(EnderecoRequestDTO dto){
        rua = dto.getRua();
        bairro = dto.getBairro();
        numero = dto.getNumero();
        cidade = dto.getCidade();
        cep = dto.getCep();
        complemento = dto.getComplemento();
        uf = dto.getUf();
    }

}
