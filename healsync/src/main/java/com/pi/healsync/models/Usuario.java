package com.pi.healsync.models;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
   
    @Column(unique = true, nullable = false)
    private String email;  
    
    @Column(unique = true, nullable = false)
    private String cpf;
    
    @Column(nullable = false)
    private String endereco;
    
    @Column(nullable = false)
    private String telefone;
    
    @Column(unique = true, nullable = false)
    private String rg;

    public Usuario(String name, String email, String cpf, String endereco, String telefone, String rg){
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.rg = rg;
    }
    
}
