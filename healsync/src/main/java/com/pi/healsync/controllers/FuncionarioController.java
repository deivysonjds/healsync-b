package com.pi.healsync.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.pi.healsync.DTO.FuncionarioRequestDTO;
import com.pi.healsync.DTO.FuncionarioResponseDTO;
import com.pi.healsync.models.Funcionario;
import com.pi.healsync.services.FuncionarioService;
import com.pi.healsync.exceptions.ObjectNotCreated;
import com.pi.healsync.exceptions.NoSuchException;


@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;



   


    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> createFuncionario(@RequestBody FuncionarioRequestDTO funcionarioRequest) {
        Funcionario funcionario = new Funcionario(funcionarioRequest);


        Funcionario savedFuncionario = funcionarioService.insert(funcionario);
        
        FuncionarioResponseDTO response = new FuncionarioResponseDTO(savedFuncionario);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> getFuncionarioById(@PathVariable UUID id) {
        Funcionario funcionario = funcionarioService.FindById(id);
        
        FuncionarioResponseDTO response = new FuncionarioResponseDTO(funcionario);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{email}")
    public ResponseEntity<FuncionarioResponseDTO> getFuncionarioByEmail(@PathVariable String email) {
        Funcionario funcionario = funcionarioService.findByEmail(email);
        
        FuncionarioResponseDTO response = new FuncionarioResponseDTO(funcionario);
        
        return ResponseEntity.ok(response);
    }

}
