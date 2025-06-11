package com.pi.healsync.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.healsync.DTO.paciente.PacienteResponseDTO;
import com.pi.healsync.models.Paciente;
import com.pi.healsync.services.SenhaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/senhas")
public class SenhaController {
    @Autowired
    private SenhaService senhaService;
    
    @GetMapping
    public ResponseEntity<PacienteResponseDTO> getMethodName(@RequestParam String senha) {
        try {
            Paciente paciente = senhaService.findPacienteBySenha(senha);
            PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO(paciente);
            return ResponseEntity.ok().body(pacienteResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
