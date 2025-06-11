package com.pi.healsync.models;
import java.util.ArrayList;
import java.util.List;
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
    @OneToMany(mappedBy = "fluxo", cascade = CascadeType.ALL)
    private List<Atendimento> atendimentos;

    @OneToMany(mappedBy = "fluxo", cascade = CascadeType.ALL)
    private List<Fila> filas = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private Unidade unidade;

    public Fluxo(FluxoRequestDTO fluxoRequestDTO, Unidade unidade) {
        this.nameEspecialist = fluxoRequestDTO.getNameSpecialist();
        this.unidade = unidade;
    }

    public void addFila(Fila fila) {
        fila.setFluxo(this);
        this.filas.add(fila);
    }
}
