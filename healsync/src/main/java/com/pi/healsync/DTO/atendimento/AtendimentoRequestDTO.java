package com.pi.healsync.DTO.atendimento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtendimentoRequestDTO {
    private String sala;
    private int ordem;
}
