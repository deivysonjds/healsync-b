package com.pi.healsync.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.healsync.DTO.unidade.UnidadeRequestDto;
import com.pi.healsync.DTO.unidade.UnidadeResponseDto;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.ObjectNotCreated;
import com.pi.healsync.models.Hospital;
import com.pi.healsync.models.Unidade;
import com.pi.healsync.security.JwtUtil;
import com.pi.healsync.services.HospitalService;
import com.pi.healsync.services.UnidadeService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UnidadeService unidadeService;
    @Autowired
    private HospitalService hospitalService;

    @PostMapping
    public ResponseEntity<UnidadeResponseDto> insert(
        @RequestBody UnidadeRequestDto unidadeRequestDto, 
        @RequestHeader("Authorization") String authToken
    )
    {
        String token = authToken.substring(7);

        UUID hospitalId = jwtUtil.extractHospitalId(token);
        Unidade unidade = new Unidade(unidadeRequestDto);
        try {
            unidade = unidadeService.insert(unidade, hospitalId);
        } catch (ObjectNotCreated e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        UnidadeResponseDto responseDto = new UnidadeResponseDto(unidade);
        return ResponseEntity.ok().body(responseDto);
    }
    
    @GetMapping
    public ResponseEntity<List<UnidadeResponseDto>> getAllByHospital(@RequestHeader("Authorization") String authToken) {
        String token = authToken.substring(7);
        UUID hospitalId;
        hospitalId = jwtUtil.extractHospitalId(token);

        List<Unidade> unidades;

        try {
            Hospital hospital = hospitalService.findByID(hospitalId);
            unidades = unidadeService.findAllByHospital(hospital);
        } catch (NoSuchException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }

        List<UnidadeResponseDto> unidadesDto = unidades
            .stream()
            .map(UnidadeResponseDto::new)
            .collect(Collectors.toList());

        return ResponseEntity.ok().body(unidadesDto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponseDto> getById(@PathVariable UUID id) {
        Unidade unidade;
        try {
            unidade = unidadeService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        UnidadeResponseDto unidadeResponseDto = new UnidadeResponseDto(unidade);

        return ResponseEntity.ok().body(unidadeResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){

        try {
            unidadeService.deleteById(id);
        } catch (NoSuchException e) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeResponseDto> update(
        @RequestBody UnidadeRequestDto unidadeRequestDto,
        @PathVariable UUID id
    )
    {      
        Unidade unidade = unidadeService.findById(id);
        if (unidade == null) {
            return ResponseEntity.notFound().build();
        }

        Unidade unidadeUpdate = new Unidade(unidadeRequestDto);
        unidadeUpdate.setId(id);

        try {
            unidade = unidadeService.update(unidadeUpdate);
        } catch (ObjectNotCreated e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        UnidadeResponseDto responseDto = new UnidadeResponseDto(unidade);
        return ResponseEntity.ok().body(responseDto);
    }
    
}
