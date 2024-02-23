package com.example.dietistaspring.repositories;

import com.example.dietistaspring.entities.Alimentos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentosRepository extends JpaRepository<Alimentos, Long> {
}
