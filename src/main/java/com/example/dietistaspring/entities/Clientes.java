package com.example.dietistaspring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany
    @JoinTable(name = "clientes_dietas", joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "dieta_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"cliente_id","dieta_id"})}
    )
    private List<Dietas> dietas;

    private String nombreUsuario;

    private String password;



}
