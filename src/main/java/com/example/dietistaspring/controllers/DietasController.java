package com.example.dietistaspring.controllers;


import com.example.dietistaspring.entities.Alimentos;
import com.example.dietistaspring.entities.Dietas;
import com.example.dietistaspring.services.AlimentosService;
import com.example.dietistaspring.services.DietasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dietas")
@Tag(name = "Dietas", description = "Dietas API REST con Operaciones CRUD")
public class DietasController {

    @Autowired
    private DietasService dietasService;

    @Autowired
    private AlimentosService alimentosService;


    @Operation(summary = "Obtener todas las dietas", description = "Devuelve una lista de todas las dietas")
    @ApiResponse(responseCode = "200", description = "Lista de dietas encontradas", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")})
    @GetMapping
    public List<Dietas> list(){return dietasService.findAll(); }

    @Operation(summary = "Obtener la dieta correspondiente al id", description = "Devuelve una dieta")
    @ApiResponse(responseCode = "200", description = "Dieta encontrada", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")})
    @GetMapping("/{id}")
    public ResponseEntity<Dietas> view(@PathVariable Long id){
        Optional<Dietas> productOptional = dietasService.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva dieta", description = "Crea una nueva dieta")
    @ApiResponse(responseCode = "201", description = "Dieta creada", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")})
    @PostMapping
    public ResponseEntity<Dietas> create(@RequestBody Dietas dietas){

        return ResponseEntity.status(HttpStatus.CREATED).body(dietasService.save(dietas));
    }

    @Operation(summary = "Actualizar una dieta por su ID", description = "Actualiza una dieta existente por su ID")
    @ApiResponse(responseCode = "200", description = "Dieta actualizada", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")})
    @PutMapping("/{id}")
    public ResponseEntity<Dietas> update(@PathVariable Long id, @RequestBody Dietas dietas){
        Optional <Dietas> productOptional = dietasService.update(id, dietas);
        if(productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una dieta por su ID", description = "Elimina una dieta existente por su ID")
    @ApiResponse(responseCode = "200", description = "Dieta eliminada", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Dietas> delete(@PathVariable Long id){
        Optional<Dietas> productOptional = dietasService.delete(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }



}
