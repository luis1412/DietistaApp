package com.example.dietistaspring.controllers;


import com.example.dietistaspring.entities.Alimentos;
import com.example.dietistaspring.entities.Dietas;
import com.example.dietistaspring.entities.Usuarios;
import com.example.dietistaspring.services.AlimentosService;
import com.example.dietistaspring.services.DietasService;
import com.example.dietistaspring.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @Autowired
    private UsuarioService usuarioService;


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
    public ResponseEntity<?> create(@RequestBody Dietas dietas){
      Optional<Usuarios> usuariosOptional = usuarioService.findById(dietas.getUsuarios().getId());
     List<Alimentos> listaAlimentos = dietas.getAlimentos();
     for (Alimentos ali : listaAlimentos){
         if (alimentosService.findById(ali.getId()).isEmpty()){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado uno de los alimentos proporcionados");
         }
     }
      if (usuariosOptional.isPresent()){
        return ResponseEntity.status(HttpStatus.CREATED).body(dietasService.save(dietas));
      }
      else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el usuario proporcionado");
      }
    }

    @Operation(summary = "Actualizar una dieta por su ID", description = "Actualiza una dieta existente por su ID")
    @ApiResponse(responseCode = "200", description = "Dieta actualizada", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")})
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Dietas dietas){
        if (dietasService.findById(id).isPresent()){
            List<Alimentos> listaAlimentos = dietas.getAlimentos();
            for (Alimentos ali : listaAlimentos){
                if (alimentosService.findById(ali.getId()).isEmpty()){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado uno de los alimentos proporcionados");
                }
            }
        Optional<Usuarios> usuariosOptional = usuarioService.findById(dietas.getUsuarios().getId());
        if (usuariosOptional.isPresent()) {
            Optional<Dietas> dietasOptional = dietasService.update(id, dietas);
            if (dietasOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(dietasOptional.orElseThrow());
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el usuario proporcionado");
        }
        }
        else if (dietasService.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La dieta proporcionada no existe");
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una dieta por su ID", description = "Elimina una dieta existente por su ID")
    @ApiResponse(responseCode = "200", description = "Dieta eliminada", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Dietas> delete(@PathVariable Long id){
        Optional<Dietas> dietasOptional = dietasService.delete(id);
        if(dietasOptional.isPresent()){
            return ResponseEntity.ok(dietasOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obtener todas las dietas que esten entre un rango de calorias", description = "Devuelve una lista de todas las dietas")
    @ApiResponse(responseCode = "200", description = "Lista de dietas encontradas", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")})
    @GetMapping("/filter/between")
    public  ResponseEntity<?>  findDietasByTotalCaloriasBetween(@RequestParam Long min, Long max){
      List<Dietas> dietas = dietasService.findDietasByTotalCaloriasBetween(min, max);
        if (dietas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay dietas entre el rango proporcioando de calorias.");
        }
        return ResponseEntity.ok(dietas);
    }
}
