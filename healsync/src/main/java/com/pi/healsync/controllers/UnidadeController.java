package com.pi.healsync.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.healsync.DTO.UnidadeRequestDto;
import com.pi.healsync.DTO.UnidadeResponseDto;
import com.pi.healsync.models.Hospital;
import com.pi.healsync.models.Unidade;
import com.pi.healsync.security.JwtUtil;
import com.pi.healsync.services.HospitalService;
import com.pi.healsync.services.UnidadeService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UnidadeService unidadeService;
    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/")
    public ResponseEntity<UnidadeResponseDto> insert(
        @RequestBody UnidadeRequestDto unidadeRequestDto, 
        @RequestHeader("Authorization") String authToken
    )
    {
        String token = authToken.substring(7);

        UUID hospitalId = jwtUtil.extractId(token);
        Unidade unidade = new Unidade(unidadeRequestDto);

        unidade = unidadeService.insert(unidade, hospitalId);

        UnidadeResponseDto responseDto = new UnidadeResponseDto(unidade);
        return ResponseEntity.ok().body(responseDto);
    }
    
    @GetMapping
    public ResponseEntity<List<UnidadeResponseDto>> getAllByHospital(@RequestHeader("Authorization") String authToken) {

        String token = authToken.substring(7);
        UUID hospitalId = jwtUtil.extractId(token);

        Hospital hospital = hospitalService.findByID(hospitalId);
        List<Unidade> unidades = unidadeService.findAllByHospital(hospital);

        List<UnidadeResponseDto> unidadesDto = unidades
            .stream()
            .map(UnidadeResponseDto::new)
            .collect(Collectors.toList());

        return ResponseEntity.ok().body(unidadesDto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponseDto> getById(@PathVariable UUID id) {

        Unidade unidade = unidadeService.findById(id);

        UnidadeResponseDto unidadeResponseDto = new UnidadeResponseDto(unidade);

        return ResponseEntity.ok().body(unidadeResponseDto);
    }
    
}
