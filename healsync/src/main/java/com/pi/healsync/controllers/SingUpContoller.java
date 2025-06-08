package com.pi.healsync.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.healsync.DTO.signUp.SignUpDTO;
import com.pi.healsync.models.Funcionario;
import com.pi.healsync.models.Hospital;
import com.pi.healsync.services.FuncionarioService;
import com.pi.healsync.services.HospitalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/signup")
public class SingUpContoller {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<?> postMethodName(@RequestBody SignUpDTO dto) {

        Hospital hospital = new Hospital();
        hospital.setName(dto.getName());
        hospital.setCnpj(dto.getCnpj());
        hospital.setEmail(dto.getEmail());
        hospital.setPhone(dto.getPhone());
        hospital = hospitalService.insert(hospital);

        Funcionario funcionario = new Funcionario();
        funcionario.setName(dto.getNameUser());
        funcionario.setEmail(dto.getEmailUser());
        funcionario.setCpf(dto.getCpf());
        funcionario.setSenha(dto.getPassword());
        funcionario.setRole(dto.getRole());
        funcionario.setHospital(hospital);
        funcionario = funcionarioService.insert(funcionario);
        
        return ResponseEntity.ok().body("Sign up successful");
    }
    
}
