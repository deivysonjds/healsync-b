package com.pi.healsync.DTO;

import java.util.UUID;

import com.pi.healsync.models.Hospital;

public class HospitalResponseDTO {
    private UUID id;
    private String name;
    private String email;

    public HospitalResponseDTO(Hospital hospital) {
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.email = hospital.getEmail();
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

}
