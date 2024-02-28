package com.example.dietistaspring.services;

import com.example.dietistaspring.entities.Alimentos;

import java.util.List;
import java.util.Optional;

public interface AlimentosService {
    List<Alimentos> findAll();
    Optional<Alimentos> findById(Long id);
    Alimentos save (Alimentos alimentos);
    Optional <Alimentos> update(Long id, Alimentos alimentos);
    void delete(Long id);
    List<Alimentos> getAlimentosByNombreContains(String name);
    List<Alimentos> getAlimentosByCaloriasBetween(Long numeroCaloriasMin, Long numeroCaloriasMax);
    List<Alimentos> getAlimentosByGrasasBetween(Double numeroCaloriasMin, Double numeroCaloriasMax);
    List<Alimentos> getAlimentosBySalBetween(Double salMin, Double salMax);
    List<Alimentos> getAlimentosByHidratosBetween(Double hidratosMin, Double hidratosMax);

}
