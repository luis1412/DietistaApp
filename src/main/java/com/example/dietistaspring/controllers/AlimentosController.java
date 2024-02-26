package com.example.dietistaspring.controllers;


import com.example.dietistaspring.entities.Alimentos;
import com.example.dietistaspring.services.AlimentosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alimentos")
@Tag(name = "Alimentos", description = "Alimentos API REST con Operaciones CRUD")
public class AlimentosController {

    @Autowired
    private AlimentosService alimentosService;


    @ApiResponses(
            value = {@ApiResponse(responseCode = "200",description = "alimentos",content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Alimentos.class)))})}
    )
    @Operation(summary = "Mostrar todos los alimentos", description = "Devuelve una lista de todos los alimentos en la base de datos")
    @GetMapping
    public List<Alimentos> list(){
        return alimentosService.findAll();
    }

    @Operation(summary = "Obtener un alimento por su ID", description = "Devuelve un alimento por su ID si existe")
    @ApiResponse(responseCode = "200", description = "Éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alimentos.class)))
    @ApiResponse(responseCode = "404", description = "No encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Alimentos> view(@PathVariable Long id ){
        Optional<Alimentos> optionalAlimentos = alimentosService.findById(id);
        if(optionalAlimentos.isPresent()){
            return ResponseEntity.ok(optionalAlimentos.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo alimento", description = "Crea un nuevo alimento en la base de datos")
    @ApiResponse(responseCode = "201", description = "Creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alimentos.class)))
    @PostMapping
    public ResponseEntity<Alimentos> create(@RequestBody Alimentos alimentos){
        return ResponseEntity.status(HttpStatus.CREATED).body(alimentosService.save(alimentos));
    }

    @Operation(summary = "Actualizar un alimento por su ID", description = "Actualiza un alimento existente por su ID")
    @ApiResponse(responseCode = "201", description = "Actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alimentos.class)))
    @ApiResponse(responseCode = "404", description = "No encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Alimentos> update(@PathVariable Long id, @RequestBody Alimentos alimentos){
        Optional <Alimentos> optionalAlimentos
                = alimentosService.update(id, alimentos);
        if(optionalAlimentos.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalAlimentos.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    @Operation(summary = "Eliminar un alimento por su ID", description = "Elimina un alimento existente por su ID")
    @ApiResponse(responseCode = "200", description = "Éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alimentos.class)))
    @ApiResponse(responseCode = "404", description = "No encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Alimentos> delete(@PathVariable Long id){
        Optional<Alimentos> alimentos = alimentosService.delete(id);
        if(alimentos.isPresent()){
            return ResponseEntity.ok(alimentos.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
