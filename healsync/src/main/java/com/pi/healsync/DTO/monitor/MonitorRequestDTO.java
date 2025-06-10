package com.pi.healsync.DTO.monitor;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonitorRequestDTO {
    private long numeroTombamento;
    private UUID atendimentoId;
}
