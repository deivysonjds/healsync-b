package com.pi.healsync.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pi.healsync.DTO.hospital.HospitalRequestDTO;
import com.pi.healsync.DTO.hospital.HospitalResponseDTO;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.models.Hospital;
import com.pi.healsync.security.JwtUtil;
import com.pi.healsync.services.HospitalService;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping(value = "/hospital")
public class HospitalController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HospitalService service;

    @PostMapping("/register")
    public ResponseEntity<HospitalResponseDTO> addHospital(@RequestBody HospitalRequestDTO dto) {

        Hospital hospital = new Hospital(dto);
        
        hospital = service.insert(hospital);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(hospital.getId())
				.toUri();
        
        HospitalResponseDTO responseDto = new HospitalResponseDTO(hospital);
        return ResponseEntity.created(uri).body(responseDto);
    }
    

    @GetMapping("/")
    public ResponseEntity<HospitalResponseDTO> getHospitalById(@RequestHeader("Authorization") String authToken) {
        Hospital hospital;
        String token = authToken.substring(7);

        UUID id = jwtUtil.extractId(token);
        try {
            hospital = service.findByID(id);
        } catch (NoSuchException e) {
            throw new NoSuchException("hospital");
        }

        HospitalResponseDTO dto = new HospitalResponseDTO(hospital);
        return ResponseEntity.ok(dto);
    }
    
}
