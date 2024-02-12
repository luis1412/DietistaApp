package com.example.dietistaspring.entities;

import jakarta.persistence.*;
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
    private Long id;

    private String contenidoComentario;

    private String titulo;

    private LocalDate fechaComentario;

    @ManyToOne
    private Clientes cliente;

    @ManyToOne
    private Dietista dietista;

}
