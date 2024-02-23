package com.example.dietistaspring.services;

import com.example.dietistaspring.entities.Clientes;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ClientesService {
    @Transactional(readOnly = true)
    List<Clientes> findAll();

    @Transactional(readOnly = true)
    Optional<Clientes> findById(Long id);

    @Transactional
    Clientes save(Clientes clientes);

}
