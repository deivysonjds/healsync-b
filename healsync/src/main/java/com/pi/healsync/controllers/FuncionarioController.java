package com.pi.healsync.controllers;

import com.pi.healsync.exceptions.ObjectNotCreated;
import com.pi.healsync.models.Hospital;
import com.pi.healsync.services.HospitalService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.pi.healsync.DTO.funcionario.FuncionarioRequestDTO;
import com.pi.healsync.DTO.funcionario.FuncionarioResponseDTO;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.models.Funcionario;
import com.pi.healsync.security.JwtUtil;
import com.pi.healsync.services.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> createFuncionario(@RequestBody FuncionarioRequestDTO dto, @RequestHeader("Authorization") String authToken) {
        String token = authToken.substring(7);
        UUID hospitalId = jwtUtil.extractHospitalId(token);  // obtém o hospitalId do token
        try {
            Funcionario funcionario = new Funcionario(dto);
            Funcionario savedFuncionario = funcionarioService.insert(funcionario, hospitalId);
            return ResponseEntity.status(HttpStatus.CREATED).body(new FuncionarioResponseDTO(savedFuncionario));
        } catch (ObjectNotCreated e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> updateFuncionario(
            @PathVariable UUID id,
            @RequestBody FuncionarioRequestDTO dto,
            @RequestHeader("Authorization") String authToken
    ) {
        try {
            Funcionario existingFuncionario = funcionarioService.findById(id);
            // Verifique se o funcionário pertence ao hospital (opcional)
            // Atualize os dados do funcionário com os do DTO
            Funcionario updatedFuncionario = funcionarioService.update(existingFuncionario, dto);
            return ResponseEntity.ok(new FuncionarioResponseDTO(updatedFuncionario));
        } catch (NoSuchException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (ObjectNotCreated e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> getAllFuncionarios(@RequestHeader("Authorization") String authToken) {
        try {
            String token = authToken.substring(7);
            UUID hospitalId = jwtUtil.extractHospitalId(token); // extrair hospitalId do token
            Hospital hospital = hospitalService.findByID(hospitalId);

            List<Funcionario> funcionarios = funcionarioService.findAllByHospital(hospital);

            List<FuncionarioResponseDTO> funcionariosDto = funcionarios.stream()
                    .map(FuncionarioResponseDTO::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(funcionariosDto);

        } catch (NoSuchException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> getFuncionarioById(@RequestHeader("Authorization") String authToken, @PathVariable UUID id) {

        try {
            Funcionario funcionario = funcionarioService.findById(id);
            return ResponseEntity.ok(new FuncionarioResponseDTO(funcionario));
        } catch (NoSuchException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable UUID id
    ){  
        try {
            
            funcionarioService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}