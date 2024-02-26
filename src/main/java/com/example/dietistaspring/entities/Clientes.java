package com.example.dietistaspring.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Schema(description = "ID del cliente", example = "1")
    private Long id;


    @ManyToMany
    @JoinTable(name = "clientes_dietas", joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "dieta_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"cliente_id","dieta_id"})}
    )
    @Schema(description = "Lista de dietas del cliente")
    private List<Dietas> dietas;

    @Column( unique = true)
    @NotBlank
    @Schema(description = "Nombre de usuario del cliente")
    @NotNull

    private String nombreUsuario;


    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(description = "Contraseña del cliente")
    @NotNull

    private String password;


    @ManyToMany
    @JoinTable(
            name="clientes_roles",
            joinColumns = @JoinColumn( name = "clientes_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"clientes_id","role_id" })}
    )
    @Schema(description = "Lista de roles del cliente")
    private List <Role> roles;

    @Schema(description = "Estado de activación del cliente")
    private boolean enabled;

}
