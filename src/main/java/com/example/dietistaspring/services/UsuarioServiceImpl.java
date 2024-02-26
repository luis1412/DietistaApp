package com.example.dietistaspring.services;

import com.example.dietistaspring.entities.Usuarios;
import com.example.dietistaspring.entities.Role;
import com.example.dietistaspring.repositories.UsuarioRepository;
import com.example.dietistaspring.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Usuarios> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuarios> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuarios save(Usuarios usuarios) {
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_DIETISTA");
        List <Role> roles = new ArrayList<>();
        optionalRoleUser.ifPresent(roles::add);
        if(usuarios.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_DADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        usuarios.setRoles(roles);
        usuarios.setPassword(passwordEncoder.encode(usuarios.getPassword()));

        return usuarioRepository.save(usuarios);
    }


}
