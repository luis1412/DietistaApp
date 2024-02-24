package com.example.dietistaspring.services;


import com.example.dietistaspring.entities.Clientes;
import com.example.dietistaspring.entities.Role;
import com.example.dietistaspring.repositories.ClientesRepository;
import com.example.dietistaspring.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientesServiceImpl implements ClientesService{

    @Autowired
    ClientesRepository clientesRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


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
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_CLIENTE");
        List <Role> roles = new ArrayList<>();
        optionalRoleUser.ifPresent(roles::add);
        clientes.setRoles(roles);
        clientes.setPassword(passwordEncoder.encode(clientes.getPassword()));
        return clientesRepository.save(clientes);
    }

}
