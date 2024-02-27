package com.example.dietistaspring.controllers;


import com.example.dietistaspring.entities.Alimentos;
import com.example.dietistaspring.services.AlimentosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



    @Operation(summary = "Buscar uno o varios alimentos que contengan la palabra clave introducida", description = "Buscar uno o varios alimentos que contengan la palabra clave introducida")
    @ApiResponse(responseCode = "200", description = "Busqueda correcta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alimentos.class)))
    @ApiResponse(responseCode = "404", description = "Alimento no encontrado")
    @GetMapping("/busqueda/nombre")
    public ResponseEntity<?> findAlimentosContainsName (@RequestParam String name){
        List<Alimentos> alimentosList = alimentosService.getAlimentosByNombreContains(name);
        if (alimentosList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ningún alimento coincide con esa descripción");
        }
            return ResponseEntity.ok(alimentosList);
    }
    @Operation(summary = "Buscar uno o varios alimentos que esten entre los valores proporcionados de calorias")
    @ApiResponse(responseCode = "200", description = "Busqueda correcta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alimentos.class)))
    @ApiResponse(responseCode = "404", description = "Alimento no encontrado")
    @GetMapping("/busqueda/betweenCalorias")
    public ResponseEntity<?> findAlimentosBetweenCalorias (@RequestParam Long min, Long max){
        List<Alimentos> alimentosList = alimentosService.getAlimentosByCaloriasBetween(min, max);
        if (alimentosList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron alimentos dentro del rango especificado.");
        }
        return ResponseEntity.ok(alimentosList);
    }
    @Operation(summary = "Buscar uno o varios alimentos que esten entre los valores proporcionados de grasas")
    @ApiResponse(responseCode = "200", description = "Busqueda correcta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alimentos.class)))
    @ApiResponse(responseCode = "404", description = "Alimento no encontrado")
    @GetMapping("/busqueda/betweenGrasas")
    public ResponseEntity<?> findAlimentosBetweenGrasas (@RequestParam Double min, Double max){
        List<Alimentos> alimentosList = alimentosService.getAlimentosByGrasasBetween(min, max);
        if (alimentosList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron alimentos dentro del rango especificado.");
        }
        return ResponseEntity.ok(alimentosList);
    }
    @Operation(summary = "Buscar uno o varios alimentos que esten entre los valores proporcionados de hidratos")
    @ApiResponse(responseCode = "200", description = "Busqueda correcta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alimentos.class)))
    @ApiResponse(responseCode = "404", description = "Alimento no encontrado")
    @GetMapping("/busqueda/betweenHidratos")
    public ResponseEntity<?> findAlimentosBetweenHidratos (@RequestParam Double min, Double max){
        List<Alimentos> alimentosList = alimentosService.getAlimentosByHidratosBetween(min, max);
        if (alimentosList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron alimentos dentro del rango especificado.");
        }
        return ResponseEntity.ok(alimentosList);
    }
    @Operation(summary = "Buscar uno o varios alimentos que esten entre los valores proporcionados de sales")
    @ApiResponse(responseCode = "200", description = "Busqueda correcta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alimentos.class)))
    @ApiResponse(responseCode = "404", description = "Alimento no encontrado")
    @GetMapping("/busqueda/betweenSal")
    public ResponseEntity<?> findAlimentosBetweenSal (@RequestParam Double min, Double max){
        List<Alimentos> alimentosList = alimentosService.getAlimentosBySalBetween(min, max);
        if (alimentosList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron alimentos dentro del rango especificado.");

        }
        return ResponseEntity.ok(alimentosList);
    }
}
