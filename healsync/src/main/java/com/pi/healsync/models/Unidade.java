package com.pi.healsync.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.pi.healsync.DTO.unidade.UnidadeRequestDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL)
    private List<Paciente> pacientes;

    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL)
    private List<Fluxo> fluxos;

    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL)
    private List<Monitor> monitores;

    public Unidade(UnidadeRequestDto unidadeRequestDto){
        name = unidadeRequestDto.getName();

        if (this.pacientes == null) {
            this.pacientes = new ArrayList<>();
        }

        if( this.funcionarios == null) {
            this.funcionarios = new ArrayList<>();
        }

        if( this.fluxos == null) {
            this.fluxos = new ArrayList<>();
        }

        if ( this.monitores == null) {
            this.monitores = new ArrayList<>();
        }
    }
}
