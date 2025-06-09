package com.pi.healsync.models;
import java.util.UUID;

import com.pi.healsync.DTO.fluxo.FluxoRequestDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fluxo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nameEspecialist;

    @ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private Unidade unidade;

    public Fluxo(FluxoRequestDTO fluxoRequestDTO, Unidade unidade) {
        this.nameEspecialist = fluxoRequestDTO.getNameSpecialist();
        this.unidade = unidade;
    }
}
