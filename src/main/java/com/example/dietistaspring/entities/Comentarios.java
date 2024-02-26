package com.example.dietistaspring.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "comentarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del comentario", example = "1")
    private Long id;
    @Schema(description = "Contenido del comentario", example = "¡Gran servicio! Muy satisfecho con la dieta personalizada.")
    @NotNull
    private String contenidoComentario;
    @Schema(description = "Título del comentario", example = "Excelente experiencia")
    @NotNull
    private String titulo;
    @Schema(description = "Fecha del comentario", example = "2024-02-26")
    @NotNull
    private LocalDate fechaComentario;
    @Schema(description = "Cliente que realizó el comentario", example = "1")
    @ManyToOne
    @NotNull
    private Clientes cliente;
    @Schema(description = "Dietista al que se le hace el comentario", example = "1")
    @ManyToOne
    @NotNull
    private Dietista dietista;

}
