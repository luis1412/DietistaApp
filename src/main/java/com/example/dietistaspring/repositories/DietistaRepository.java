package com.example.dietistaspring.repositories;

import com.example.dietistaspring.entities.Dietista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DietistaRepository extends JpaRepository<Dietista, Long> {
    Optional<Dietista> findByUsername(String username);

}
