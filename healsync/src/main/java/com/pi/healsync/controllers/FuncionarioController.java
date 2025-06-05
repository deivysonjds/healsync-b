package com.pi.healsync.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pi.healsync.DTO.funcionario.FuncionarioRequestDTO;
import com.pi.healsync.DTO.funcionario.FuncionarioResponseDTO;
import com.pi.healsync.models.Funcionario;
import com.pi.healsync.services.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/register")
    public ResponseEntity<FuncionarioResponseDTO> createFuncionario(@RequestBody FuncionarioRequestDTO funcionarioRequest) {
        Funcionario funcionario = new Funcionario(funcionarioRequest);

        Funcionario savedFuncionario = funcionarioService.insert(funcionario);

        FuncionarioResponseDTO response = new FuncionarioResponseDTO(savedFuncionario);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<FuncionarioResponseDTO> getFuncionarioById(
        @RequestParam(required = false) UUID id,
        @RequestParam(required = false) String email
        ) {

        if(id != null){
            Funcionario funcionario = funcionarioService.findById(id);
            FuncionarioResponseDTO response = new FuncionarioResponseDTO(funcionario);
            return ResponseEntity.ok(response);
        }

        if(email != null){
            Funcionario funcionario = funcionarioService.findByEmail(email);
            FuncionarioResponseDTO response = new FuncionarioResponseDTO(funcionario);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}
