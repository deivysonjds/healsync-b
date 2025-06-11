package com.pi.healsync.DTO.funcionario;
import com.pi.healsync.models.Endereco;
import lombok.NoArgsConstructor;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioRequestDTO {

    private String name;
    private String email;
    private int age;
    private String cpf;
    private String phone;
    private Endereco endereco;
    private String rg;
    private String role;
    private String password;
    private UUID hospitalId;

}
