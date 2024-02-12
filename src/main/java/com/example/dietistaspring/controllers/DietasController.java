package com.example.dietistaspring.controllers;


import com.example.dietistaspring.entities.Dietas;
import com.example.dietistaspring.services.DietasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dietas")
public class DietasController {

    @Autowired
    private DietasService dietasService;


    @GetMapping
    public List<Dietas> list(){return dietasService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Dietas> view(@PathVariable Long id){
        Optional<Dietas> productOptional = dietasService.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


}
