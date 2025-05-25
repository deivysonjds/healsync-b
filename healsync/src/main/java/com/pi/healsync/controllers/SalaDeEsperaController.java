package main.java.com.pi.healsync.controllers;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pi.healsync.dtos.SalaDeEsperaRequestDTO;
import com.pi.healsync.dtos.SalaDeEsperaResponseDTO;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.models.SalaDeEspera;
import com.pi.healsync.services.SalaDeEsperaService;

@RestController
@RequestMapping("/salas")
public class SalaDeEsperaController {

    @Autowired
    private SalaDeEsperaService service;

    @PostMapping("/register")
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

        try {
            sala = service.findById(id);
        } catch (NoSuchException e) {
            throw new NoSuchException("Sala de Espera");
        }

        SalaDeEsperaResponseDTO dto = new SalaDeEsperaResponseDTO(sala.getId(), sala.getSala());
        return ResponseEntity.ok(dto);
    }
}
