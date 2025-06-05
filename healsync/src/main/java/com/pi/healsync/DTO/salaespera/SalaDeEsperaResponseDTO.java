package com.pi.healsync.DTO.salaespera;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SalaDeEsperaResponseDTO {

    private UUID id;
    private String sala;

    public SalaDeEsperaResponseDTO() {
    }

    public SalaDeEsperaResponseDTO(UUID id, String sala) {
        this.id = id;
        this.sala = sala;
    }
}
