package com.pi.healsync.DTO.endereco;

import lombok.Getter;

import com.pi.healsync.models.Endereco;

import java.util.UUID;

@Getter
public class EnderecoResponseDTO {
    private UUID id;
    private String rua;
    private String bairro;
    private int numero;
    private String cidade;
    private int cep;
    private String complemento;
    private String uf;

    public EnderecoResponseDTO(Endereco endereco){
        this.id = endereco.getId();
        this.rua = endereco.getRua();
        this.bairro = endereco.getBairro();
        this.numero = endereco.getNumero();
        this.cidade = endereco.getCidade();
        this.cep = endereco.getCep();
        this.complemento = endereco.getComplemento();
        this.uf = endereco.getUf();
    }
}
