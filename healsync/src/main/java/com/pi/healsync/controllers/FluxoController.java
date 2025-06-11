package com.pi.healsync.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.pi.healsync.DTO.fluxo.FluxoRequestDTO;
import com.pi.healsync.DTO.fluxo.FluxoResponseDTO;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.models.Fluxo;
import com.pi.healsync.models.Unidade;
import com.pi.healsync.services.FluxoService;
import com.pi.healsync.services.UnidadeService;

@RestController
@RequestMapping("fluxos")
public class FluxoController {
    @Autowired
    private FluxoService service;
    @Autowired
    private UnidadeService unidadeService;

    @PostMapping
    public ResponseEntity <FluxoResponseDTO> postFluxo (@RequestBody FluxoRequestDTO fluxo) {
        Unidade unidade = unidadeService.findById(fluxo.getUnidadeId());
        Fluxo savedFluxo = service.insert(new Fluxo(fluxo, unidade));
        FluxoResponseDTO savedFluxoResponse = new FluxoResponseDTO(savedFluxo);
        return ResponseEntity.ok().body(savedFluxoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity <FluxoResponseDTO> update (
        @RequestBody FluxoRequestDTO dto,
        @PathVariable UUID id
        ) {
        Fluxo fluxo = service.findById(id);
        if (fluxo == null) {
            return ResponseEntity.notFound().build();
        }
        Unidade unidade = unidadeService.findById(dto.getUnidadeId());
        Fluxo fluxoUpdate = new Fluxo(dto, unidade);
        fluxoUpdate.setId(id);
        Fluxo savedFluxo = service.update(fluxoUpdate);
        FluxoResponseDTO savedFluxoResponse = new FluxoResponseDTO(savedFluxo);
        
        return ResponseEntity.ok().body(savedFluxoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity <FluxoResponseDTO> getById (@PathVariable UUID id) {
        Fluxo getFluxo = service.findById(id);
        return ResponseEntity.ok().body(new FluxoResponseDTO(getFluxo));
    }

    @DeleteExchange("/{id}")
    public ResponseEntity <Void> delete (@PathVariable UUID id) {
        try {
            service.deleteById(id);
            
        } catch (NoSuchException e) {
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity <List<FluxoResponseDTO>> getAllByUnidade (@RequestParam(required = true) UUID unidadeId) {
        try {
            Unidade unidade = unidadeService.findById(unidadeId);
            List <Fluxo> fluxos = service.getAllByUnidade(unidade);
            List <FluxoResponseDTO> fluxosResponse = fluxos
                    .stream()
                    .map(FluxoResponseDTO::new)
                    .toList();
            return ResponseEntity.ok().body(fluxosResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
