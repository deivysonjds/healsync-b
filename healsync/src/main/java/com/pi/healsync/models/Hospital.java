package com.pi.healsync.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.pi.healsync.DTO.hospital.HospitalRequestDTO;

@Entity
@Setter
@Getter
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String cnpj;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phone;

    @Column
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Unidade> unidades = new ArrayList<>();

    public Hospital(){}

    public Hospital(HospitalRequestDTO dto){
        name = dto.getName();
        cnpj = dto.getCnpj();
        email = dto.getEmail();
        phone = dto.getPhone();
    }
    
    public Hospital(String name, String cnpj, String email, String phone) {
        this.name = name;
        this.cnpj = cnpj;
        this.email = email;
        this.phone = phone;
    }
}
