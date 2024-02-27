package com.example.dietistaspring.repositories;

import com.example.dietistaspring.entities.Dietas;
import com.example.dietistaspring.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DietasRepository extends JpaRepository<Dietas, Long> {

    @Query(value = "SELECT d.* FROM dietas d WHERE (SELECT SUM(a.calorias) FROM alimentos a, dietas_alimentos da WHERE a.id = da.alimento_id AND da.dieta_id = d.id) BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Dietas> findDietasByTotalCaloriasBetween(Long minCalorias, Long maxCalorias);

}
