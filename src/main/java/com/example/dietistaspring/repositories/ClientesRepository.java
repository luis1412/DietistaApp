package com.example.dietistaspring.repositories;

import com.example.dietistaspring.entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientesRepository  extends JpaRepository<Clientes, Long> {
    Optional<Clientes> findByNombreUsuario(String nombreUsuario);

}
