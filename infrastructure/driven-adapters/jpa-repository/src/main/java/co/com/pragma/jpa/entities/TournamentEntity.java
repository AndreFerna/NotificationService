package co.com.pragma.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity(name = "torneo")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TournamentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "fecha_inicio")
    private LocalDateTime startDate;
    @Column(name = "fecha_fin")
    private LocalDateTime endDate;
    @Column(name = "codigo_unico")
    private String uniqueCode;
}
