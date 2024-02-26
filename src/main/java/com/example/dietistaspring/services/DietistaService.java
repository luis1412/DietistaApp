package com.example.dietistaspring.services;

import com.example.dietistaspring.entities.Dietista;

import java.util.List;
import java.util.Optional;

public interface DietistaService {

    List<Dietista> findAll();
    Optional<Dietista> findById(Long id);
    Dietista save (Dietista dietista);

}
