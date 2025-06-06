package com.pi.healsync.models;

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
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id",nullable = false)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    public Unidade(String name, Endereco endereco){
        this.name = name;
        this.endereco = endereco;
    }

    public Unidade(UnidadeRequestDto unidadeRequestDto){
        name = unidadeRequestDto.getName();
        endereco = unidadeRequestDto.getEndereco();
    }
}
