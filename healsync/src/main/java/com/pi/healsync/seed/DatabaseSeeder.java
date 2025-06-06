package com.pi.healsync.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.pi.healsync.models.Funcionario;
import com.pi.healsync.models.Hospital;
import com.pi.healsync.models.Roles;
import com.pi.healsync.services.FuncionarioService;
import com.pi.healsync.services.HospitalService;

@Component
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Override
    public void run(String... args){
    
        Hospital hospital = new Hospital();
        hospital.setName("Restauração");
        hospital.setCnpj("12345678000195");
        hospital.setEmail("restauracao@gmail.com");
        hospital.setPhone("81912345679");
        
        hospitalService.insert(hospital);
        
        Funcionario funcionario = new Funcionario();
        funcionario.setName("julia");
        funcionario.setEmail("teste@gmail.com");
        funcionario.setCpf("10293847381");
        funcionario.setEndereco("rua 2, n 11, arruda - recife PE");
        funcionario.setTelefone("81937467283");
        funcionario.setRg("10836746");
        funcionario.setRole(Roles.valueOf("ADMIN"));
        funcionario.setSenha("teste123@");
        
        funcionarioService.insert(funcionario);
        
        
        
    }
}
