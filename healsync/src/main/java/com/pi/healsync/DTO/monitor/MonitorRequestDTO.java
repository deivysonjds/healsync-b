package com.pi.healsync.DTO.monitor;

import com.pi.healsync.models.Monitor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonitorRequestDTO {
    private long numeroTombamento;
   
    public MonitorRequestDTO(){}

    public MonitorRequestDTO(Monitor monitor){
      numeroTombamento = monitor.getNumeroTombamento();
        
    }
}
