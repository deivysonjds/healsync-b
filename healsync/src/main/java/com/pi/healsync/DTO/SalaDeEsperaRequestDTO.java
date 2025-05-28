package com.pi.healsync.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaDeEsperaRequestDTO {

    private String sala;

    public SalaDeEsperaRequestDTO() {
    }

    public SalaDeEsperaRequestDTO(String sala) {
        this.sala = sala;
    }
}
