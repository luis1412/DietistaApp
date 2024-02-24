package com.example.dietistaspring.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @Column( unique = true)
    @NotBlank
    private String nombreUsuario;


    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @ManyToMany
    @JoinTable(
            name="clientes_roles",
            joinColumns = @JoinColumn( name = "clientes_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"clientes_id","role_id" })}
    )
    private List <Role> roles;
    private boolean enabled;

}
