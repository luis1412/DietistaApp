package com.example.dietistaspring.controllers;

import com.example.dietistaspring.entities.Dietista;
import com.example.dietistaspring.services.DietistaService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/dietista")
@Tag(name = "Dietista", description = "Dietista API REST con Operaciones CRUD")
public class DietistaController {


    @Autowired
    DietistaService dietistaService;

    @Operation(summary = "Listar todos los dietistas", description = "Obtiene una lista de todos los dietistas registrados")
    @ApiResponse(responseCode = "200", description = "Lista de dietistas obtenida", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    @GetMapping
    public List<Dietista> list(){
        return dietistaService.findAll();
    }


    @Operation(summary = "Crear un nuevo dietista", description = "Crea un nuevo dietista")
    @ApiResponse(responseCode = "201", description = "Dietista creado", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Dietista dietista, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dietistaService.save(dietista));
    }

    @Operation(summary = "Registrar un nuevo dietista", description = "Registra un nuevo dietista")
    @ApiResponse(responseCode = "201", description = "Dietista registrado", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
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
