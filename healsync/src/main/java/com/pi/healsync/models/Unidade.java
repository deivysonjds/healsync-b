package com.pi.healsync.models;

import java.util.UUID;

import com.pi.healsync.DTO.unidade.UnidadeRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Column(nullable = false)
    private String endereco;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    public Unidade(String name, String endereco){
        this.name = name;
        this.endereco = endereco;
    }

    public Unidade(UnidadeRequestDto unidadeRequestDto){
        name = unidadeRequestDto.getName();
        endereco = unidadeRequestDto.getEndereco();
    }
}
