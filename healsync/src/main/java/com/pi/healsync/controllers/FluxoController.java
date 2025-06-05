package com.pi.healsync.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.healsync.models.Fluxo;
import com.pi.healsync.services.FluxoService;

@RestController
@RequestMapping("fluxos")
public class FluxoController {
    @Autowired
    private FluxoService service;

    @PostMapping
    public ResponseEntity <Fluxo> postFluxo (Fluxo fluxo) {
        Fluxo savedFluxo = service.insert(fluxo);
        return ResponseEntity.ok().body(savedFluxo);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Fluxo> getById (@PathVariable UUID id) {
        Fluxo getFluxo = service.findById(id);
        return ResponseEntity.ok().body(getFluxo);
    }
    
    @GetMapping
    public ResponseEntity <List<Fluxo>> getAll () {
        List <Fluxo> fluxos = service.getAll();
        return ResponseEntity.ok().body(fluxos);
    }
}
