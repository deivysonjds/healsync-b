package main.java.com.pi.healsync.DTO;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaDeEsperaRequestDTO {

    @NotBlank(message = "O nome da sala é obrigatório.")
    private String sala;

    public SalaDeEsperaRequestDTO() {
    }

    public SalaDeEsperaRequestDTO(String sala) {
        this.sala = sala;
    }
}
