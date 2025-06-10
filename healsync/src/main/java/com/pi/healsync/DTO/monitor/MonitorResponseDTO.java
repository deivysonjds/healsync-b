package com.pi.healsync.DTO.monitor;

import lombok.Getter;

import java.util.UUID;

import com.pi.healsync.models.Monitor;

@Getter
public class MonitorResponseDTO {
	public UUID id;
	private long numeroTombamento;

	public MonitorResponseDTO(Monitor monitor) {
		this.id = monitor.getId();
		this.numeroTombamento = monitor.getNumeroTombamento();
	}
}
