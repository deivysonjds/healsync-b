package com.pi.healsync.controllers;

import com.pi.healsync.DTO.monitor.MonitorRequestDTO;
import com.pi.healsync.DTO.monitor.MonitorResponseDTO;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.models.Atendimento;
import com.pi.healsync.models.Monitor;
import com.pi.healsync.security.JwtUtil;
import com.pi.healsync.services.AtendimentoService;
import com.pi.healsync.services.MonitorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/monitor")
public class MonitorController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MonitorService service;
    @Autowired
    private AtendimentoService atendimentoService;

    @PostMapping
        public ResponseEntity<MonitorResponseDTO> addMonitor(@RequestBody MonitorRequestDTO dto){
        Atendimento atendimento;
        try {
            atendimento = atendimentoService.findById(dto.getAtendimentoId());
        } catch (NoSuchException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        Monitor monitor = new Monitor(dto, atendimento);

        monitor = service.insert(monitor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand()
                .toUri();

        MonitorResponseDTO responseDto = new MonitorResponseDTO(monitor);
        return ResponseEntity.created(uri).body(responseDto);
    }

    @GetMapping("")
    public ResponseEntity<MonitorResponseDTO> getMonitorById(@RequestHeader("Authorization") String authToken) {

        Monitor monitor;
        String token = authToken.substring(7);

        UUID id = jwtUtil.extractId(token);
        try {
            monitor = service.findById(id);
        } catch (NoSuchException e) {
            throw new NoSuchException("Monitor");
        }

        MonitorResponseDTO dto = new MonitorResponseDTO(monitor);
        return ResponseEntity.ok(dto);
    }
}
