package com.example.dietistaspring.services;

import com.example.dietistaspring.entities.Comentarios;
import com.example.dietistaspring.entities.Usuarios;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ComentariosService {

    List<Comentarios> findAll();
    Optional<Comentarios> findById(Long id);
    Comentarios save (Comentarios comentarios);
    Optional <Comentarios> update(Long id, Comentarios comentarios);
    Optional<Comentarios> delete(Long id);

    List<Comentarios> getComentariosByFechaComentarioBetween(LocalDate fecha1, LocalDate fecha2);

    List<Comentarios> getComentariosByUsuariosDestinatarioAndFechaComentarioBetween(Usuarios usuarios, LocalDate fecha1, LocalDate fecha2);
    List<Comentarios> getComentariosByUsuariosAndFechaComentarioBetween(@NotNull Usuarios usuarios, @NotNull LocalDate fechaComentario, @NotNull LocalDate fechaComentario2);


}
