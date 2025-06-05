package com.pi.healsync.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pi.healsync.DTO.paciente.PacienteRequestDTO;
import com.pi.healsync.DTO.paciente.PacienteResponseDTO;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.ObjectNotCreated;
import com.pi.healsync.models.Paciente;
import com.pi.healsync.services.PacienteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;
    
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> insert(@RequestBody PacienteRequestDTO dto) {
        
        Paciente entity = new Paciente(dto);
        try {
            entity = pacienteService.insert(entity);
        } catch (ObjectNotCreated e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        PacienteResponseDTO responseDto = new PacienteResponseDTO(entity);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDto.getId())
				.toUri();

        return ResponseEntity.created(uri).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> getById(@PathVariable UUID id) {
        Paciente paciente;
        try {
            paciente = pacienteService.findById(id);
        } catch (NoSuchException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
        PacienteResponseDTO responseDto = new PacienteResponseDTO(paciente);
        return ResponseEntity.ok().body(responseDto);
    }
    
    @GetMapping
    public ResponseEntity<List<Paciente>> getAll() {
        
        List<Paciente> pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        
        return ResponseEntity.ok().body(pacientes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        try {
            pacienteService.deleteById(id);
        } catch (NoSuchException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
        return ResponseEntity.noContent().build();
    }
    
}
