package com.example.dietistaspring.entities;
import com.example.dietistaspring.entities.Alimentos;
import com.example.dietistaspring.entities.Usuarios;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "dietas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dietas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de la dieta", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @Schema(description = "usuario asignado a la dieta", example = "1")
    @NotNull
    private Usuarios usuarios;


    @ManyToMany
    @JoinTable(name = "dietas_alimentos", joinColumns = @JoinColumn(name = "dieta_id"),
                inverseJoinColumns = @JoinColumn(name = "alimento_id"),
                    uniqueConstraints = {@UniqueConstraint(columnNames = {"dieta_id","alimento_id"})}
                    )
    @Schema(description = "Lista de alimentos asociados a la dieta")
    @NotNull
    private List<Alimentos> alimentos;

    @NotNull
    @Schema(description = "Fecha de creación de la dieta", example = "2024-02-26")
    private LocalDate fechaCreacion;

}
