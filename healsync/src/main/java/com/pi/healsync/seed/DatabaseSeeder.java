package com.pi.healsync.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.pi.healsync.models.Endereco;
import com.pi.healsync.models.Funcionario;
import com.pi.healsync.models.Hospital;
import com.pi.healsync.models.Unidade;
import com.pi.healsync.services.FuncionarioService;
import com.pi.healsync.services.HospitalService;
import com.pi.healsync.services.UnidadeService;

@Component
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private UnidadeService unidadeService;

    @Override
    public void run(String... args){
        
        // HOSPITAL
        Hospital hospital = new Hospital();
        hospital.setName("Restauração");
        hospital.setCnpj("12345678000195");
        hospital.setEmail("restauracao@gmail.com");
        hospital.setPhone("81912345679");
        hospital = hospitalService.insert(hospital);

        // ENDEREÇO
        Endereco endereco = new Endereco();
        endereco.setCep("50000000");
        endereco.setRua("Rua 1");
        endereco.setNumero(10);
        endereco.setBairro("peixinhos");
        endereco.setCidade("Recife");
        endereco.setUf("PE");
        endereco.setComplemento("casa");

        // UNIDADE
        Unidade unidade = new Unidade();
        unidade.setName("Unidade 1");
        unidade.setEndereco(endereco);
        unidade = unidadeService.insert(unidade, hospital.getId());

        // FUNCIONÁRIO
        Funcionario funcionario = new Funcionario();
        funcionario.setName("julia");
        funcionario.setEmail("teste@gmail.com");
        funcionario.setCpf("10293847381");
        funcionario.setTelefone("81937467283");
        funcionario.setRg("10836746");
        funcionario.setRole("ADMIN");
        funcionario.setSenha("teste123@");
        funcionario.setHospital(hospital);
        funcionario.setUnidade(unidade);
        funcionarioService.insert(funcionario);
        
    }
}
