package com.pi.healsync.DTO.atendimento;

import java.util.UUID;

import com.pi.healsync.models.Atendimento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoResponseDTO {
    private UUID id;
    private String sala;
    private int ordem;
    private String typeSala;

    public AtendimentoResponseDTO(Atendimento atendimento) {
        id = atendimento.getId();
        sala = atendimento.getSala();
        ordem = atendimento.getOrdem();
        typeSala = atendimento.getTypeSala().name();
    }
}
