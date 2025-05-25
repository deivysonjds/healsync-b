package main.java.com.pi.healsync.models;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SalaDeEspera {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String sala;

    public SalaDeEspera() {
    }

    public SalaDeEspera(String id, String sala) {
        this.id = id;
        this.sala = sala;
    }
}
