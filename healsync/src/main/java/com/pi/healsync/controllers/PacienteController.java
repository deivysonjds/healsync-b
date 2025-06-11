package com.pi.healsync.controllers;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pi.healsync.DTO.paciente.PacienteRequestDTO;
import com.pi.healsync.DTO.paciente.PacienteResponseDTO;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.ObjectNotCreated;
import com.pi.healsync.models.Endereco;
import com.pi.healsync.models.Fila;
import com.pi.healsync.models.Fluxo;
import com.pi.healsync.models.Paciente;
import com.pi.healsync.models.Senha;
import com.pi.healsync.services.FilaService;
import com.pi.healsync.services.FluxoService;
import com.pi.healsync.services.PacienteService;
import com.pi.healsync.services.SenhaService;

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
    @Autowired
    private FluxoService fluxoService;
    @Autowired
    private FilaService filaService;
    @Autowired
    private SenhaService senhaService;

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> insert(
        @RequestBody PacienteRequestDTO dto,
        @RequestParam(required = true) UUID fluxoId
    ) {
        LocalDate date = LocalDate.now();
        Fluxo fluxo;
        try {
            fluxo = fluxoService.findById(fluxoId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (!filaService.isFilaExists(date)) {
            Fila fila = new Fila();
            fila.setFluxo(fluxo);
            fluxo.addFila(fila);
            filaService.insert(fila);
            fluxoService.insert(fluxo);
        }
        Endereco endereco = new Endereco(dto.getEndereco());
        Paciente paciente;
        try {
            paciente = pacienteService.findByCpf(dto.getCpf());
            paciente.setEndereco(endereco);
        } catch (Exception e) {
            paciente = new Paciente(dto);
            paciente.setEndereco(endereco);
            try {
                paciente = pacienteService.insert(paciente);
            } catch (ObjectNotCreated er) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
        
        Fila fila = filaService.getFilaByDate(date);
        Senha senha = fila.addSenha(paciente);
        
        senhaService.insert(senha);
        filaService.insert(fila);

        PacienteResponseDTO responseDto = new PacienteResponseDTO(paciente);
        responseDto.setSenha(senha.getSenha());

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
