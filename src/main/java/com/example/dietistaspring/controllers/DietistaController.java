package com.example.dietistaspring.controllers;

import com.example.dietistaspring.entities.Dietista;
import com.example.dietistaspring.services.DietistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/dietista")
public class DietistaController {


    @Autowired
    DietistaService dietistaService;

    @GetMapping
    public List<Dietista> list(){
        return dietistaService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Dietista dietista, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dietistaService.save(dietista));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Dietista dietista, BindingResult result){
        return create(dietista, result);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo "+ err.getField()+ " "+ err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
