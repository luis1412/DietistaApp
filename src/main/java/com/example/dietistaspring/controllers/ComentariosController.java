package com.example.dietistaspring.controllers;


import com.example.dietistaspring.entities.Comentarios;
import com.example.dietistaspring.services.ComentariosService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios")
@Tag(name = "Comentarios", description = "Comentarios API REST con Operaciones CRUD")
public class ComentariosController {

    @Autowired
    private ComentariosService comentariosService;

    @Operation(summary = "Listar todos los comentarios", description = "Devuelve una lista de todos los comentarios")
    @ApiResponse(responseCode = "200", description = "Lista de comentarios encontrados", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comentarios.class))))
    @GetMapping
    public List<Comentarios> list(){
        return comentariosService.findAll();
    }


    @Operation(summary = "Obtener un comentario por su ID", description = "Devuelve un comentario por su ID si existe")
    @ApiResponse(responseCode = "200", description = "Comentario encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comentarios.class)))
    @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Comentarios> view(@PathVariable Long id){
        Optional<Comentarios> productOptional = comentariosService.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    @Operation(summary = "Crear un nuevo comentario", description = "Crea un nuevo comentario")
    @ApiResponse(responseCode = "201", description = "Comentario creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comentarios.class)))
    @PostMapping
    public ResponseEntity<Comentarios> create(@RequestBody Comentarios comentarios){
        return ResponseEntity.status(HttpStatus.CREATED).body(comentariosService.save(comentarios));
    }

    @Operation(summary = "Actualizar un comentario por su ID", description = "Actualiza un comentario existente por su ID")
    @ApiResponse(responseCode = "200", description = "Comentario actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comentarios.class)))
    @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Comentarios> update(@PathVariable Long id, @RequestBody Comentarios comentarios){
        Optional <Comentarios> productOptional = comentariosService.update(id, comentarios);
        if(productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un comentario por su ID", description = "Elimina un comentario existente por su ID")
    @ApiResponse(responseCode = "200", description = "Comentario eliminado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comentarios.class)))
    @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Comentarios> delete(@PathVariable Long id){
        Optional<Comentarios> comentariosOptional = comentariosService.delete(id);
        if(comentariosOptional.isPresent()){
            return ResponseEntity.ok(comentariosOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }





}
