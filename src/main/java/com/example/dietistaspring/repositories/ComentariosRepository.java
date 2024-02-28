package com.example.dietistaspring.repositories;

import com.example.dietistaspring.entities.Comentarios;
import com.example.dietistaspring.entities.Usuarios;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {

      List<Comentarios> getComentariosByFechaComentarioBetween(LocalDate fecha1, LocalDate fecha2);
      List<Comentarios> getComentariosByUsuariosDestinatarioAndFechaComentarioBetween(Usuarios usuarios, LocalDate fecha1, LocalDate fecha2);
      List<Comentarios> getComentariosByUsuariosAndFechaComentarioBetween(@NotNull Usuarios usuarios, @NotNull LocalDate fechaComentario, @NotNull LocalDate fechaComentario2);

}
