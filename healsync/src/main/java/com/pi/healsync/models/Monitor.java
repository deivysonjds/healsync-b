package com.pi.healsync.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import com.pi.healsync.DTO.monitor.MonitorRequestDTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Monitor {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false, unique = true)
	private long numeroTombamento;

	@Column(nullable = false)
	private boolean inUse;

	@ManyToOne
	@JoinColumn(name = "unidade_id", nullable = false)
	private Unidade unidade;

	@ManyToOne
	@JoinColumn(name = "atendimento_id", nullable = false)
	private Atendimento atendimento;

	public Monitor(MonitorRequestDTO dto, Atendimento atendimento) {
		this.unidade = atendimento.getUnidade();
		this.numeroTombamento = dto.getNumeroTombamento();
		this.atendimento = atendimento;
		this.inUse = false;
	}

}
