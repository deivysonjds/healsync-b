package com.pi.healsync.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import com.pi.healsync.DTO.monitor.MonitorRequestDTO;

@Entity
@Getter
@Setter
public class Monitor {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false, unique = true)
	private long numeroTombamento;

	@ManyToOne
	@JoinColumn(name = "unidade_id", nullable = false)
	private Unidade unidade;

	@ManyToOne
	@JoinColumn(name = "sala_id", nullable = true)
	private SalaDeEspera sala;

	public Monitor(MonitorRequestDTO dto) {
		numeroTombamento = dto.getNumeroTombamento();

	}

}
