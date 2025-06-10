package com.pi.healsync.models;

import java.util.List;
import java.util.UUID;

import com.pi.healsync.DTO.atendimento.AtendimentoRequestDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Atendimento {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID )
    private UUID id;
    @Column(nullable = false)
    private int ordem;
    @Column(nullable = false)
    private String sala;

    @ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private Unidade unidade;

    @ManyToOne
    @JoinColumn(name = "fluxo_id", nullable = false)
    private Fluxo fluxo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeSala typeSala;

    @OneToMany(mappedBy = "atendimento", cascade = CascadeType.ALL)
    private List<Monitor> monitors;

    public Atendimento(AtendimentoRequestDTO dto, Fluxo fluxo) {
        ordem = dto.getOrdem();
        sala = dto.getSala();
        typeSala = TypeSala.valueOf(dto.getTypeSala().toUpperCase());
        this.unidade = fluxo.getUnidade();
        this.fluxo = fluxo;
    }
}
