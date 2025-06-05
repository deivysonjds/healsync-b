package com.pi.healsync.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import com.pi.healsync.DTO.monitor.MonitorRequestDTO;
@Entity
@Getter
@Setter
public class Monitor {
    @Id
  private long numeroTombamento;

  @OneToOne
  @JoinColumn(name="unidade_id",nullable=false)
  private Unidade unidade;
  @OneToOne
  @JoinColumn(name="salaEspera_id",nullable=true)
  private SalaDeEspera salaEspera;
  @OneToOne
  @JoinColumn(name="atendimento_id",nullable=true)
  private Atendimento atendimento;

  public Monitor(MonitorRequestDTO dto){
    numeroTombamento=dto.getNumeroTombamento();
        
    }

  public Monitor(long numeroTombamento) {
    this.numeroTombamento = numeroTombamento;
      
  }
  
  
}
