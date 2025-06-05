package com.pi.healsync.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import com.pi.healsync.DTO.MonitorRequestDTO;
@Entity
@Getter
@Setter
public class Monitor {
    @Id
  private long numeroTombamento;

  @OneToOne
  @JoinColumn(Name="unidade_id",nullable=false)
  private Unidade unidade;
  @OnetoOne
  @JoinColumn(name="salaEspera_id",nullable=true)
  private SalaEspera salaEspera;
  @OnetoOne
  @JoinColumn(name="atendimento_id",nullable=true)
  private Atendimento atendimento;

  public Monitor(MonitorRequestDTO dto){
    numeroTombamento=dto.getNumeroTombamento;
        
    }

    public Monitor(long numeroTombamento) {
      this.numeroTombamento = numeroTombamento;
        
    }
  
  
}
