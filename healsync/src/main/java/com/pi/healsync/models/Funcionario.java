package com.pi.healsync.models;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoargsConstructor
@AllArgsConstructor
public class Funcionario extends Usuario {

    private String role;
    private String senha;

    public Funcionario() {
        super();
    }

    public Funcionario(FuncionarioRequestDTO funcionarioRequestDTO) {
        super(funcionarioRequestDTO.getName(), funcionarioRequestDTO.getEmail(), funcionarioRequestDTO.getCpf(),
              funcionarioRequestDTO.getEndereco(), funcionarioRequestDTO.getTelefone(), funcionarioRequestDTO.getRg());
        this.role = funcionarioRequestDTO.getRole();
        this.senha = funcionarioRequestDTO.getSenha();
    }

    public Funcionario(String name, String email, String cpf, String endereco, String telefone, String rg, String role, String senha) {
        super(name, email, cpf, endereco, telefone, rg);
        this.role = role;
        this.senha = senha;
        


}
    
}