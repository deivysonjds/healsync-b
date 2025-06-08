package com.pi.healsync.controllers;

import com.pi.healsync.DTO.endereco.EnderecoRequestDTO;
import com.pi.healsync.DTO.endereco.EnderecoResponseDTO;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.models.Endereco;

import com.pi.healsync.services.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping
        public ResponseEntity<EnderecoResponseDTO> addEndereco(@RequestBody EnderecoRequestDTO dto){
        
        Endereco endereco = new Endereco(dto);

        endereco = service.insert(endereco);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(endereco.getId())
                .toUri();

        EnderecoResponseDTO responseDto = new EnderecoResponseDTO(endereco);
        return ResponseEntity.created(uri).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> getEnderecoById(@PathVariable UUID id) {
        Endereco endereco;
        try {
            endereco = service.findById(id);
        } catch (NoSuchException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        EnderecoResponseDTO dto = new EnderecoResponseDTO(endereco);
        return ResponseEntity.ok().body(dto);

    }

}
