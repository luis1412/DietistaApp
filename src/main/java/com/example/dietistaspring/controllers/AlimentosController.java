package com.example.dietistaspring.controllers;


import com.example.dietistaspring.entities.Alimentos;
import com.example.dietistaspring.services.AlimentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alimentos")
public class AlimentosController {

    @Autowired
    private AlimentosService alimentosService;

    @GetMapping
    public List<Alimentos> list(){
        return alimentosService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Alimentos> view(@PathVariable Long id){
        Optional<Alimentos> optionalAlimentos = alimentosService.findById(id);
        if(optionalAlimentos.isPresent()){
            return ResponseEntity.ok(optionalAlimentos.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Alimentos> create(@RequestBody Alimentos alimentos){
        return ResponseEntity.status(HttpStatus.CREATED).body(alimentosService.save(alimentos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alimentos> update(@PathVariable Long id, @RequestBody Alimentos alimentos){
        Optional <Alimentos> optionalAlimentos
                = alimentosService.update(id, alimentos);
        if(optionalAlimentos.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalAlimentos.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Alimentos> delete(@PathVariable Long id){
        Optional<Alimentos> alimentos = alimentosService.delete(id);
        if(alimentos.isPresent()){
            return ResponseEntity.ok(alimentos.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
