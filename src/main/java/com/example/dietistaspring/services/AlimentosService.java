package com.example.dietistaspring.services;

import com.example.dietistaspring.entities.Alimentos;

import java.util.List;
import java.util.Optional;

public interface AlimentosService {
    List<Alimentos> findAll();
    Optional<Alimentos> findById(Long id);
    Alimentos save (Alimentos alimentos);
    Optional <Alimentos> update(Long id, Alimentos alimentos);
    Optional<Alimentos> delete(Long id);

}
