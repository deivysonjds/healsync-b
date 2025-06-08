package com.pi.healsync.DTO.signUp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {

    // dados de hospital
    private String name;
    private String cnpj;
    private String email;
    private String phone;

    // dados de funcionario
    private String nameUser;
    private String emailUser;
    private int age;
    private String cpf;
    private String phoneUser;
    private String rg;
    private String password;
}
