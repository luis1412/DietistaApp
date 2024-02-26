package com.example.dietistaspring.controllers;


import com.example.dietistaspring.entities.Clientes;
import com.example.dietistaspring.services.ClientesService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Clientes API REST con Operaciones CRUD")
public class ClientesController {

    @Autowired
    ClientesService clientesService;

    @Operation(summary = "Listar todos los clientes", description = "Devuelve una lista de todos los clientes en el sistema")
    @ApiResponse(responseCode = "200", description = "Éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Clientes.class)))
    @GetMapping
    public List<Clientes> list(){
        return clientesService.findAll();
    }

    @Operation(summary = "Crear un nuevo cliente", description = "Crea un nuevo cliente en el sistema")
    @ApiResponse(responseCode = "201", description = "Creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Clientes.class)))
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Clientes clientes, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clientesService.save(clientes));
    }

    @Operation(summary = "Registrar un nuevo cliente", description = "Registra un nuevo cliente en el sistema")
    @ApiResponse(responseCode = "201", description = "Creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Clientes.class)))
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Clientes clientes, BindingResult result){
        return create(clientes, result);
    }

    // Método privado para formatear los errores de validación
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo "+ err.getField()+ " "+ err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

}
