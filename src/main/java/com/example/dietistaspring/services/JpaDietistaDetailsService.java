package com.example.dietistaspring.services;

import com.example.dietistaspring.entities.Dietista;
import com.example.dietistaspring.repositories.DietistaRepository;
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
    private DietistaRepository dietistaRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Dietista> userOptional = dietistaRepository.findByUsername(username);

        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException((String.format("Username %s no existe", username)));
        }
        Dietista dietista = userOptional.orElseThrow();

        List<GrantedAuthority> authorities = dietista.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(dietista.getUsername(),
                dietista.getPassword(),
                dietista.isEnabled(),
                true,
                true,
                true,
                authorities);

    }

}
