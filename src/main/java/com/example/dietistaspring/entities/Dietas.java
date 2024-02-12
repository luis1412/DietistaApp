package com.example.dietistaspring.entities;

import jakarta.persistence.*;
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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dietista_id")
    private Dietista dietista;


    @ManyToMany
    @JoinTable(name = "dietas_alimentos", joinColumns = @JoinColumn(name = "dieta_id"),
                inverseJoinColumns = @JoinColumn(name = "alimento_id"),
                    uniqueConstraints = {@UniqueConstraint(columnNames = {"dieta_id","alimento_id"})}
                    )
    private List<Alimentos> alimentos;

    private LocalDate fechaCreacion;

}
