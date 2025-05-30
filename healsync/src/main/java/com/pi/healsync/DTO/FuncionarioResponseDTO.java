package com.pi.healsync.DTO;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioResponseDTO {
    private String name;
    private String email;
    private String cpf;
    private String endereco;
    private String telefone;
    private String rg;
    private String role;

    public FuncionarioResponseDTO(Funcionario funcionario) {
        this.name = funcionario.getName();
        this.email = funcionario.getEmail();
        this.cpf = funcionario.getCpf();
        this.endereco = funcionario.getEndereco();
        this.telefone = funcionario.getTelefone();
        this.rg = funcionario.getRg();
        this.role = funcionario.getRole();
    }

 

}
