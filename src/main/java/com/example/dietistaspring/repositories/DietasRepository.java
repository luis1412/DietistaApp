package com.example.dietistaspring.repositories;

import com.example.dietistaspring.entities.Dietas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DietasRepository extends JpaRepository<Dietas, Long> {

}
