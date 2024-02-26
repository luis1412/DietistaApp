package com.example.dietistaspring.entities;
import com.example.dietistaspring.entities.Role;
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
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del usuario", example = "1")
    private Long id;


    @Column(unique = true)
    @NotBlank
    @NotNull
    @Size(min = 4, max = 16)
    @Schema(description = "Nombre de usuario", example = "johndoe")
    private String username;

    @NotBlank
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 4, max = 100)
    @Schema(description = "Contraseña del usuario", example = "hola123")
    private String password;


    @JsonIgnore
    @OneToMany(mappedBy = "", cascade = CascadeType.ALL)
    @Schema(description = "Lista de dietas asociadas al usuario")
    private List<Usuarios> diets;


    @ManyToMany
    @JoinTable(
            name="usuario_roles",
            joinColumns = @JoinColumn( name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","role_id" })}
    )
    @Schema(description = "Lista de roles del usuario")
    private List<Role>roles;

    @Schema(description = "Indica si el usuario está habilitado", example = "true")

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
