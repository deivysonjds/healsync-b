package com.pi.healsync.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pi.healsync.DTO.HospitalRequestDTO;
import com.pi.healsync.DTO.HospitalResponseDTO;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.models.Hospital;
import com.pi.healsync.services.HospitalService;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value = "hospital")
public class HospitalController {

    @Autowired
    private HospitalService service;

    @PostMapping("/register")
    public ResponseEntity<HospitalResponseDTO> addHospital(@RequestBody HospitalRequestDTO dto) {
        System.out.println(dto);
        Hospital hospital = new Hospital(dto);

        HospitalResponseDTO hospitalDTO = service.insert(hospital);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(hospitalDTO.getId())
				.toUri();
        return ResponseEntity.created(uri).body(hospitalDTO);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponseDTO> getHospitalById(@PathVariable UUID id) {
        HospitalResponseDTO hospitalDTO;
            try {
                hospitalDTO = service.findByID(id);
            } catch (NoSuchException e) {
                throw new NoSuchException("hospital");
            }

        return ResponseEntity.ok(hospitalDTO);
    }
    
}
