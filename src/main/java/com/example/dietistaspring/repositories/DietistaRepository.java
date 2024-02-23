package com.example.dietistaspring.repositories;

import com.example.dietistaspring.entities.Dietista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietistaRepository extends JpaRepository<Dietista, Long> {
}
