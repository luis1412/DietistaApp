package com.example.dietistaspring.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "dietista")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dietista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del dietista", example = "1")
    private Long id;


    @Column(unique = true)
    @NotBlank
    @NotNull
    @Size(min = 4, max = 16)
    @Schema(description = "Nombre de usuario del dietista", example = "johndoe")
    private String username;

    @NotBlank
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 4, max = 100)
    @Schema(description = "Contraseña del dietista", example = "hola123")
    private String password;


    @JsonIgnore
    @OneToMany(mappedBy = "dietista", cascade = CascadeType.ALL)
    @Schema(description = "Lista de dietas asociadas al dietista")
    private List<Dietas> diets;


    @ManyToMany
    @JoinTable(
            name="dietista_roles",
            joinColumns = @JoinColumn( name = "dietista_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"dietista_id","role_id" })}
    )
    @Schema(description = "Lista de roles del dietista")
    private List<Role>roles;

    @Schema(description = "Indica si el dietista está habilitado", example = "true")

    private boolean enabled;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;

    @PrePersist
    public void prePresist()
    {
        enabled = true;
    }

}
