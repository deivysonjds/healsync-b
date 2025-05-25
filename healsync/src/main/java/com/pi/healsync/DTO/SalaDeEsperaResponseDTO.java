package main.java.com.pi.healsync.DTO;

import java.util.UUID;

public class SalaDeEsperaResponseDTO {

    private UUID id;
    private String sala;

    public SalaDeEsperaResponseDTO() {
    }

    public SalaDeEsperaResponseDTO(UUID id, String sala) {
        this.id = id;
        this.sala = sala;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
}
