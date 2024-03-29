package com.example.dietistaspring.services;

import com.example.dietistaspring.entities.Comentarios;
import com.example.dietistaspring.entities.Usuarios;
import com.example.dietistaspring.repositories.ComentariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ComentariosServiceImpl implements ComentariosService {

    @Autowired
    ComentariosRepository comentariosRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Comentarios> findAll() {
        return comentariosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)

    public Optional<Comentarios> findById(Long id) {
        return comentariosRepository.findById(id);
    }

    @Override
    @Transactional
    public Comentarios save(Comentarios comentarios) {
        return comentariosRepository.save(comentarios);
    }

    @Override
    @Transactional
    public Optional<Comentarios> update(Long id, Comentarios comentarios) {
        Optional <Comentarios> comentariosOptional = comentariosRepository.findById(id);
        if(comentariosOptional.isPresent()){
            Comentarios comentariosDb = comentariosOptional.orElseThrow();
            comentariosDb.setContenidoComentario(comentarios.getContenidoComentario());
            comentariosDb.setFechaComentario(comentarios.getFechaComentario());
            comentariosDb.setTitulo(comentarios.getTitulo());
            comentariosDb.setUsuarios(comentarios.getUsuarios());
            return Optional.of(comentariosRepository.save(comentariosDb));
        }
        return comentariosOptional;
    }

    @Override
    @Transactional
    public Optional<Comentarios> delete(Long id) {
        Optional <Comentarios> comentariosOptional = comentariosRepository.findById(id);
        comentariosOptional.ifPresent( comentarios -> comentariosRepository.delete(comentarios));
        return comentariosOptional;
    }

    @Override
    public List<Comentarios> getComentariosByFechaComentarioBetween(LocalDate fecha1, LocalDate fecha2) {
        return comentariosRepository.getComentariosByFechaComentarioBetween(fecha1, fecha2);
    }

    @Override
    public List<Comentarios> getComentariosByUsuariosDestinatarioAndFechaComentarioBetween(Usuarios usuarios, LocalDate fecha1, LocalDate fecha2) {
        return comentariosRepository.getComentariosByUsuariosDestinatarioAndFechaComentarioBetween(usuarios, fecha1, fecha2);
    }

    @Override
    public List<Comentarios> getComentariosByUsuariosAndFechaComentarioBetween(Usuarios usuarios, LocalDate fechaComentario, LocalDate fechaComentario2) {
        return comentariosRepository.getComentariosByUsuariosAndFechaComentarioBetween(usuarios,fechaComentario, fechaComentario2);
    }
}
