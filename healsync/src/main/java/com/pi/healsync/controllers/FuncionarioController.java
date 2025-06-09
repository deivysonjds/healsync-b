package com.pi.healsync.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

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
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> createFuncionario(@RequestBody FuncionarioRequestDTO funcionarioRequest) {
        Funcionario funcionario = new Funcionario(funcionarioRequest);

        Funcionario savedFuncionario = funcionarioService.insert(funcionario);

        FuncionarioResponseDTO response = new FuncionarioResponseDTO(savedFuncionario);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<FuncionarioResponseDTO> getFuncionarioById(@RequestHeader("Authorization") String authToken) {

        String token = authToken.substring(7);
        UUID userId = jwtUtil.extractId(token);
        Funcionario funcionario;
        try {
            funcionario = funcionarioService.findById(userId);
        } catch (NoSuchException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        FuncionarioResponseDTO funcionarioResponseDTO = new FuncionarioResponseDTO(funcionario);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(funcionarioResponseDTO);
    }

}
