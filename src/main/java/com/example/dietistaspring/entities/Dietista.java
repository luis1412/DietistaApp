package com.example.dietistaspring.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private Long id;


    @Column(unique = true)
    @NotBlank
    @Size(min = 4, max = 16)
    private String username;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 4, max = 100)
    private String password;


    @JsonIgnore
    @OneToMany(mappedBy = "dietista", cascade = CascadeType.ALL)
    private List<Dietas> diets;


    @ManyToMany
    @JoinTable(
            name="dietista_roles",
            joinColumns = @JoinColumn( name = "dietista_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"dietista_id","role_id" })}
    )
    private List<Role>roles;

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
