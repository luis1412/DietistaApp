package com.example.dietistaspring.services;

import com.example.dietistaspring.entities.Comentarios;

import java.util.List;
import java.util.Optional;

public interface ComentariosService {

    List<Comentarios> findAll();
    Optional<Comentarios> findById(Long id);
    Comentarios save (Comentarios comentarios);
    Optional <Comentarios> update(Long id, Comentarios comentarios);
    Optional<Comentarios> delete(Long id);



}
