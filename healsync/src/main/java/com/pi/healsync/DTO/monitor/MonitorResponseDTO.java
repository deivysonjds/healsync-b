package com.pi.healsync.DTO.monitor;

import lombok.Getter;

import com.pi.healsync.models.Monitor;

@Getter
public class MonitorResponseDTO {
    private long numeroTombamento;
   
    public MonitorResponseDTO(Monitor monitor){
      this.numeroTombamento = monitor.getNumeroTombamento();
      
       
    }
}
