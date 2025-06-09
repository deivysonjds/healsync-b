package com.pi.healsync.DTO.fluxo;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FluxoRequestDTO {
    private String nameSpecialist;
    private UUID unidadeId;
}
