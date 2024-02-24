package com.example.dietistaspring.services;

import com.example.dietistaspring.entities.Dietista;
import com.example.dietistaspring.entities.Role;
import com.example.dietistaspring.repositories.DietistaRepository;
import com.example.dietistaspring.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DietistaServiceImpl implements DietistaService{
    @Autowired
    private DietistaRepository dietistaRepository;


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Dietista> findAll() {
        return dietistaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Dietista> findById(Long id) {
        return dietistaRepository.findById(id);
    }

    @Override
    public Dietista save(Dietista dietista) {
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_DIETISTA");
        List <Role> roles = new ArrayList<>();
        optionalRoleUser.ifPresent(roles::add);
        if(dietista.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_DADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        dietista.setRoles(roles);
        dietista.setPassword(passwordEncoder.encode(dietista.getPassword()));

        return dietistaRepository.save(dietista);
    }


}
