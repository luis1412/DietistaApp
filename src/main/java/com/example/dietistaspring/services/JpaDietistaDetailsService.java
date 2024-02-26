package com.example.dietistaspring.services;

import com.example.dietistaspring.entities.Usuarios;
import com.example.dietistaspring.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaDietistaDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuarios> userOptional = usuarioRepository.findByUsername(username);

        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException((String.format("Username %s no existe", username)));
        }
        Usuarios usuarios = userOptional.orElseThrow();

        List<GrantedAuthority> authorities = usuarios.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(usuarios.getUsername(),
                usuarios.getPassword(),
                usuarios.isEnabled(),
                true,
                true,
                true,
                authorities);

    }

}
