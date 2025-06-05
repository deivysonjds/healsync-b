package com.pi.healsync.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
}
