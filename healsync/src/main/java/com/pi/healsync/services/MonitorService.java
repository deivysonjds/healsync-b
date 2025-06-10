package com.pi.healsync.services;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.ObjectNotCreated;
import com.pi.healsync.models.Monitor;
import com.pi.healsync.repositories.MonitorRepository;

@Service
public class MonitorService {

	@Autowired
	private MonitorRepository monitorRepository;

	@Transactional
	public Monitor insert(Monitor monitor) {

		try {
			return monitorRepository.save(monitor);
		} catch (Exception e) {
			throw new ObjectNotCreated(e);
		}
	}

	@Transactional(readOnly = true)
	public Monitor findById(UUID id) {
		Optional<Monitor> monitor = monitorRepository.findById(id);

		if (!monitor.isPresent()) {
			throw new NoSuchException("Monitor");
		}

		return monitor.get();

	}

	@Transactional(readOnly = true)
	public List<Monitor> findAllByAtendimento(UUID atendimentoId) {
		List<Monitor> monitors = monitorRepository.findAllByAtendimentoId(atendimentoId);
		return monitors;
	}
}
