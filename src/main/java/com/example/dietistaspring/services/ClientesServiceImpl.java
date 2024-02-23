package com.example.dietistaspring.services;


import com.example.dietistaspring.entities.Clientes;
import com.example.dietistaspring.repositories.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientesServiceImpl implements ClientesService{

    @Autowired
    ClientesRepository clientesRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Clientes> findAll() {
        return clientesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Clientes> findById(Long id) {
        return clientesRepository.findById(id);
    }

    @Override
    @Transactional
    public Clientes save(Clientes clientes) {
        return clientesRepository.save(clientes);
    }

}
