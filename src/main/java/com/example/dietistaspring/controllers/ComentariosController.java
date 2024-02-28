package com.example.dietistaspring.controllers;


import com.example.dietistaspring.entities.Comentarios;
import com.example.dietistaspring.entities.Usuarios;
import com.example.dietistaspring.services.ComentariosService;
import com.example.dietistaspring.services.UsuarioService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios")
@Tag(name = "Comentarios", description = "Comentarios API REST con Operaciones CRUD")
public class ComentariosController {

    @Autowired
    private ComentariosService comentariosService;

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Listar todos los comentarios", description = "Devuelve una lista de todos los comentarios")
    @ApiResponse(responseCode = "200", description = "Lista de comentarios encontrados", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comentarios.class))))
    @GetMapping
    public List<Comentarios> list(){
        return comentariosService.findAll();
    }

    @Operation(summary = "Listar todos los comentarios", description = "Devuelve una lista de todos los comentarios")
    @ApiResponse(responseCode = "200", description = "Lista de comentarios encontrados", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comentarios.class))))
    @ApiResponse(responseCode = "404", description = "Lista de comentarios no encontrados", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comentarios.class))))
    @GetMapping("/filter/betweenDate")
    public ResponseEntity<?> comentariosBetweenDate(@RequestParam LocalDate fechaMin, @RequestParam LocalDate fechaMax){
        List<Comentarios> comentariosList = comentariosService.getComentariosByFechaComentarioBetween(fechaMin, fechaMax);
        if (comentariosList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron comentarios dentro del rango de fechas especificado.");
        }
        return ResponseEntity.ok(comentariosList);
    }


    @Operation(summary = "Listar todos los comentarios", description = "Devuelve una lista de todos los comentarios de un unico usuario")
    @ApiResponse(responseCode = "200", description = "Lista de comentarios encontrados", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comentarios.class))))
    @ApiResponse(responseCode = "404", description = "Lista de comentarios no encontrados", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comentarios.class))))
    @GetMapping("/filter/{id}/betweenDateDietista")
    public ResponseEntity<?> comentariosBetweenDateDietistaId(@PathVariable Long id, @RequestParam LocalDate fechaMin, @RequestParam LocalDate fechaMax){
        Optional<Usuarios> usuariosOptional = usuarioService.findById(id);
        if (usuariosOptional.isPresent()){
            List<Comentarios> comentariosList = comentariosService.getComentariosByUsuariosAndFechaComentarioBetween(usuariosOptional.get(),fechaMin, fechaMax);
            if (comentariosList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron comentarios dentro del rango de fechas especificado, para el Dietista "+ usuariosOptional.get().getUsername());
            }
            return ResponseEntity.ok(comentariosList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el usuario especificado");
    }

    @Operation(summary = "Listar todos los comentarios", description = "Devuelve una lista de todos los comentarios de un unico usuario")
    @ApiResponse(responseCode = "200", description = "Lista de comentarios encontrados", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comentarios.class))))
    @ApiResponse(responseCode = "404", description = "Lista de comentarios no encontrados", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comentarios.class))))
    @GetMapping("/filter/{id}/betweenDateClient")
    public ResponseEntity<?> comentariosBetweenDateClientId(@PathVariable Long id, @RequestParam LocalDate fechaMin, @RequestParam LocalDate fechaMax){
        Optional<Usuarios> usuariosOptional = usuarioService.findById(id);
        if (usuariosOptional.isPresent()){
        List<Comentarios> comentariosList = comentariosService.getComentariosByUsuariosDestinatarioAndFechaComentarioBetween(usuariosOptional.get(),fechaMin, fechaMax);
            if (comentariosList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron comentarios dentro del rango de fechas especificado, para el Cliente "+ usuariosOptional.get().getUsername());
            }
            return ResponseEntity.ok(comentariosList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el usuario especificado");
    }


    @Operation(summary = "Obtener un comentario por su ID", description = "Devuelve un comentario por su ID si existe")
    @ApiResponse(responseCode = "200", description = "Comentario encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comentarios.class)))
    @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Comentarios> productOptional = comentariosService.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el comentario con el id proporcionado");
    }


    @Operation(summary = "Crear un nuevo comentario", description = "Crea un nuevo comentario")
    @ApiResponse(responseCode = "201", description = "Comentario creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comentarios.class)))
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Comentarios comentarios){
        try {
        Optional<Usuarios> usuario = usuarioService.findById(comentarios.getUsuarios().getId());
        Optional<Usuarios> usuario2 = usuarioService.findById(comentarios.getUsuariosDestinatario().getId());
        if (usuario.isPresent() && usuario2.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(comentariosService.save(comentarios));
        }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado los usuarios proporcionados");
    }

    @Operation(summary = "Actualizar un comentario por su ID", description = "Actualiza un comentario existente por su ID")
    @ApiResponse(responseCode = "200", description = "Comentario actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comentarios.class)))
    @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Comentarios comentarios){
        Optional <Comentarios> productOptional = comentariosService.update(id, comentarios);
        try {
            Optional<Usuarios> usuario = usuarioService.findById(comentarios.getUsuarios().getId());
            Optional<Usuarios> usuario2 = usuarioService.findById(comentarios.getUsuariosDestinatario().getId());
            if (usuario.isPresent() && usuario2.isPresent()){
                if(productOptional.isPresent()) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
                }
            }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado los usuarios proporcionados");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha podido actualizar");
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
