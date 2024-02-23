package com.example.dietistaspring.repositories;

import com.example.dietistaspring.entities.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {
}
