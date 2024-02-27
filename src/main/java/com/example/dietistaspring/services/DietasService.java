package com.example.dietistaspring.services;

import com.example.dietistaspring.entities.Dietas;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface DietasService {

    List<Dietas> findAll();

    Dietas save(Dietas dieta);

    Optional<Dietas> findById(Long id);


    Optional <Dietas> update(Long id, Dietas dietas);
    Optional<Dietas> delete(Long id);

    List<Dietas> findDietasByTotalCaloriasBetween(Long minCalorias, Long maxCalorias);


}
