package com.pi.healsync.DTO.funcionario;
import lombok.NoArgsConstructor;
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
    private String cpf;
    private String endereco;
    private String telefone;
    private String rg;
    private String role;
    private String senha;

}
