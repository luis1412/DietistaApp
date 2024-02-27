package com.example.dietistaspring.repositories;

import com.example.dietistaspring.entities.Alimentos;
import com.example.dietistaspring.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlimentosRepository extends JpaRepository<Alimentos, Long> {

    List<Alimentos> getAlimentosByNombreContains(String name);
    List<Alimentos> getAlimentosByCaloriasBetween(Long numeroCaloriasMin, Long numeroCaloriasMax);
    List<Alimentos> getAlimentosByGrasasBetween(Double numeroCaloriasMin, Double numeroCaloriasMax);
    List<Alimentos> getAlimentosBySalBetween(Double salMin, Double salMax);
    List<Alimentos> getAlimentosByHidratosBetween(Double hidratosMin, Double hidratosMax);

}
