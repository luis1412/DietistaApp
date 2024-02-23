package com.example.dietistaspring.controllers;


import com.example.dietistaspring.entities.Comentarios;
import com.example.dietistaspring.services.ComentariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios")
public class ComentariosController {

    @Autowired
    private ComentariosService comentariosService;

    @GetMapping
    public List<Comentarios> list(){
        return comentariosService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Comentarios> view(@PathVariable Long id){
        Optional<Comentarios> productOptional = comentariosService.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Comentarios> create(@RequestBody Comentarios comentarios){
        return ResponseEntity.status(HttpStatus.CREATED).body(comentariosService.save(comentarios));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentarios> update(@PathVariable Long id, @RequestBody Comentarios comentarios){
        Optional <Comentarios> productOptional = comentariosService.update(id, comentarios);
        if(productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comentarios> delete(@PathVariable Long id){
        Optional<Comentarios> comentariosOptional = comentariosService.delete(id);
        if(comentariosOptional.isPresent()){
            return ResponseEntity.ok(comentariosOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


}
