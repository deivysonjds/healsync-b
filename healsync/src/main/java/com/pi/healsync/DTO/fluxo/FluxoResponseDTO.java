package com.pi.healsync.DTO.fluxo;

import java.util.UUID;

import com.pi.healsync.models.Fluxo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FluxoResponseDTO {
    private UUID id;
    private String nameSpecialist;

    public FluxoResponseDTO(Fluxo fluxo) {
        this.id = fluxo.getId();
        this.nameSpecialist = fluxo.getNameEspecialist();
    }
}
