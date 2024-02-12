package com.example.dietistaspring.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alimentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alimentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Long calorias;

    private Double grasas;

    private Double hidratos;

    private Double proteinas;

    private Double sal;


}
