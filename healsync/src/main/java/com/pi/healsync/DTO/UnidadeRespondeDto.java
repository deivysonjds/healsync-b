package com.pi.healsync.DTO;

import java.util.UUID;

import com.pi.healsync.models.Unidade;

import lombok.Getter;

@Getter
public class UnidadeRespondeDto {

    private UUID id;
    private String name;

    public UnidadeRespondeDto (Unidade unidade){
        id = unidade.getId();
        name = unidade.getName();
    }
}
