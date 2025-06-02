package com.pi.healsync.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import com.pi.healsync.DTO.EnderecoRequestDTO;

@Entity
@Getter
@Setter
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
    private int cep;
    private String complemento;
    @Column(nullable = false)
    private String uf;

    @OneToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private Unidade unidade;

    public Endereco(){
        id = UUID.randomUUID();
    }

    public Endereco(EnderecoRequestDTO dto){
        rua = dto.getRua();
        bairro = dto.getBairro();
        numero = dto.getNumero();
        cidade = dto.getCidade();
        cep = dto.getCep();
        complemento = dto.getComplemento();
        uf = dto.getUf();
    }

    public Endereco(String rua, String bairro, int numero, String cidade, int cep, String complemento, String uf) {
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cidade = cidade;
        this.cep = cep;
        this.complemento = complemento;
        this.uf = uf;
    }

}
