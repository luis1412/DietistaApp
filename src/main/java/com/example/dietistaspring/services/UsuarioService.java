package com.example.dietistaspring.services;

import com.example.dietistaspring.entities.Usuarios;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuarios> findAll();
    Optional<Usuarios> findById(Long id);
    Usuarios save (Usuarios usuarios, boolean admin);
    Optional<Usuarios> findUserByName(String name);
    void delete(Long id);

}
