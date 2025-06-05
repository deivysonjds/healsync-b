package com.pi.healsync.DTO.hospital;

import java.util.UUID;

import com.pi.healsync.models.Hospital;

import lombok.Getter;

@Getter
public class HospitalResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private String phone;

    public HospitalResponseDTO(Hospital hospital) {
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.email = hospital.getEmail();
        this.phone = hospital.getPhone();
    }

}
