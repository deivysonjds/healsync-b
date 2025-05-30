package com.pi.healsync.DTO;

import com.pi.healsync.models.Hospital;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalRequestDTO {
    private String name;
    private String cnpj;
    private String email;
    private String password;
    private String phone;

    public HospitalRequestDTO(){}
    
    public HospitalRequestDTO(Hospital hospital){
        name = hospital.getName();
        cnpj = hospital.getCnpj();
        email = hospital.getEmail();
        phone = hospital.getPhone();
    }
}
