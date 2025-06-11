package com.pi.healsync.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fila {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fluxo_id", nullable = false)
    private Fluxo fluxo;
    
    @Column(nullable = false)
    private LocalDate date = LocalDate.now();

    @OneToMany(mappedBy = "fila")
    private List<Senha> senhas = new ArrayList<>();

    public Senha addSenha(Paciente paciente) {
        Senha senha = new Senha(this.fluxo.getNameEspecialist(), this.senhas.size() + 1, paciente, this);
        senhas.add(senha);
        return senha;
    }
}
