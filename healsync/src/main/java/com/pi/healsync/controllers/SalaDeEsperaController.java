package com.pi.healsync.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pi.healsync.DTO.salaespera.SalaDeEsperaRequestDTO;
import com.pi.healsync.DTO.salaespera.SalaDeEsperaResponseDTO;
import com.pi.healsync.models.SalaDeEspera;
import com.pi.healsync.services.SalaDeEsperaService;

@RestController
@RequestMapping("/salas")
public class SalaDeEsperaController {

    @Autowired
    private SalaDeEsperaService service;

    @PostMapping
    public ResponseEntity<SalaDeEsperaResponseDTO> addSala(@RequestBody SalaDeEsperaRequestDTO dto) {
        SalaDeEspera sala = new SalaDeEspera(UUID.randomUUID(), dto.getSala());
        sala = service.insert(sala);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sala.getId())
                .toUri();

        SalaDeEsperaResponseDTO responseDto = new SalaDeEsperaResponseDTO(sala.getId(), sala.getSala());
        return ResponseEntity.created(uri).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDeEsperaResponseDTO> getSalaById(@PathVariable UUID id) {
        SalaDeEspera sala;

        sala = service.findById(id);

        SalaDeEsperaResponseDTO dto = new SalaDeEsperaResponseDTO(sala.getId(), sala.getSala());
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<SalaDeEsperaResponseDTO>> getAllSalas() {
        List<SalaDeEspera> salas = service.findAll();

        // Converter para DTOs
        List<SalaDeEsperaResponseDTO> dtos = salas.stream()
                .map(sala -> new SalaDeEsperaResponseDTO(sala.getId(), sala.getSala()))
                .toList();

        return ResponseEntity.ok(dtos);
    }
}
