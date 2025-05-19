package com.pi.healsync.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import com.pi.healsync.DTO.HospitalRequestDTO;

@Entity
@Setter
@Getter
public class Hospital {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String cnpj;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String phone;

    public Hospital(){
        id = UUID.randomUUID();
    }

    public Hospital(HospitalRequestDTO dto){
        id = UUID.randomUUID();
        name = dto.getName();
        cnpj = dto.getCnpj();
        email = dto.getEmail();
        phone = dto.getPhone();
        password = dto.getPassword();
    }
    
    public Hospital(String name, String cnpj, String email, String password, String phone) {
        id = UUID.randomUUID();
        this.name = name;
        this.cnpj = cnpj;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
