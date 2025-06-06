package com.pi.healsync.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.healsync.DTO.atendimento.AtendimentoRequestDTO;
import com.pi.healsync.DTO.atendimento.AtendimentoResponseDTO;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.models.Atendimento;
import com.pi.healsync.services.AtendimentoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {
    @Autowired
    private AtendimentoService atendimentoService;

    @PostMapping
    public ResponseEntity<AtendimentoResponseDTO> insert(@RequestBody AtendimentoRequestDTO dto) {
        
        Atendimento atendimento = new Atendimento(dto);
        try {
            atendimento = atendimentoService.insert(atendimento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
        AtendimentoResponseDTO responseDto = new AtendimentoResponseDTO(atendimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Atendimento> getById(@PathVariable UUID id) {
        Atendimento atendimento;
        try {
            atendimento = atendimentoService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
        return ResponseEntity.ok().body(atendimento);
    }

    @GetMapping
    public ResponseEntity<List<Atendimento>> getAll() {
        List<Atendimento> atendimentos = atendimentoService.findAll();
        if (atendimentos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok().body(atendimentos);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        try {
            atendimentoService.deleteById(id);
        } catch (NoSuchException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
