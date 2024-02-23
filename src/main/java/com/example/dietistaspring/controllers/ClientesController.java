package com.example.dietistaspring.controllers;


import com.example.dietistaspring.entities.Clientes;
import com.example.dietistaspring.services.ClientesService;
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
@RequestMapping("/api/clientes")
public class ClientesController {

    @Autowired
    ClientesService clientesService;

    @GetMapping
    public List<Clientes> list(){
        return clientesService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Clientes clientes, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clientesService.save(clientes));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Clientes clientes, BindingResult result){
        return create(clientes, result);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo "+ err.getField()+ " "+ err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

}
