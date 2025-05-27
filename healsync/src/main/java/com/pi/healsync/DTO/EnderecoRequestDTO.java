package com.pi.healsync.DTO;

import com.pi.healsync.models.Endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequestDTO {
    private String rua;
    private String bairro;
    private int numero;
    private String cidade;
    private int cep;
    private String complemento;
    private String uf;

    public EnderecoRequestDTO(){}

    public EnderecoRequestDTO(Endereco endereco){
        rua = endereco.getRua();
        bairro = endereco.getBairro();
        numero = endereco.getNumero();
        cidade = endereco.getCidade();
        cep = endereco.getCep();
        complemento = endereco.getComplemento();
        uf = endereco.getUf();
    }
}