package com.example.dietistaspring.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "alimentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alimentos {

    @Schema(example = "3", description = "Identificador de alimento numero autoincremental")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Schema(example = "Manzana", description = "Nombre del alimento")
    @NotNull
    private String nombre;

    @Schema(example = "30", description = "Cantidad numerica epxpresada en Kcal de calorias que tiene el alimento")
    @NotNull
    private Long calorias;

    @Schema(example = "24", description = "Cantidad numerica expresada en gramos de grasas que tiene el alimento")
    @NotNull

    private Double grasas;

    @Schema(example = "5", description = "Cantidad numerica expresada en gramos de hidratos que tiene el alimento")
    @NotNull

    private Double hidratos;

    @Schema(example = "6", description = "Cantidad numerica expresada en gramos de proteinas que tiene el alimento")
    @NotNull

    private Double proteinas;

    @Schema(example = "20", description = "Cantidad numerica expresada en gramos de sal que tiene el alimento")
    @NotNull

    private Double sal;


}
