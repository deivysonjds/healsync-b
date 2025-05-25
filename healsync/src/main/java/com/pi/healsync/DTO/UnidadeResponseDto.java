package com.pi.healsync.DTO;

import java.util.UUID;

import com.pi.healsync.models.Unidade;

import lombok.Getter;

@Getter
public class UnidadeResponseDto {

    private UUID id;
    private String name;

    public UnidadeResponseDto (Unidade unidade){
        id = unidade.getId();
        name = unidade.getName();
    }
}
