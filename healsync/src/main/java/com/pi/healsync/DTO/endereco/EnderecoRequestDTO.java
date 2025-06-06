package com.pi.healsync.DTO.endereco;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequestDTO {
    private String rua;
    private String bairro;
    private int numero;
    private String cidade;
    private String cep;
    private String complemento;
    private String uf;
    private UUID unidade_id;
}