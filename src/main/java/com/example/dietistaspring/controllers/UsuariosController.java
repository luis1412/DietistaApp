package com.example.dietistaspring.controllers;
import com.example.dietistaspring.entities.Role;
import com.example.dietistaspring.entities.Usuarios;
import com.example.dietistaspring.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/usuario")
@Tag(name = "usuario", description = "usuario API REST con Operaciones CRUD")
public class UsuariosController {


    @Autowired
    UsuarioService usuarioService;

    @Operation(summary = "Listar todos los usuarios", description = "Obtiene una lista de todos los usuarios registrados")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    @GetMapping
    public List<Usuarios> list(){
        return usuarioService.findAll();
    }


    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario")
    @ApiResponse(responseCode = "201", description = "usuario creado", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Usuarios usuarios, BindingResult result){
       String username = usuarios.getUsername();
       Optional<Usuarios> usuario = usuarioService.findUserByName(username);

       if (usuario.isEmpty()){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarios, usuarios.isAdmin()));
       }
        return ResponseEntity.status(HttpStatus.CREATED).body("El usuario ya existe no se puede añadir elige otro nombre de usuario");
    }

    @Operation(summary = "Registrar un nuevo usuario", description = "Registra un nuevo usuario")
    @ApiResponse(responseCode = "201", description = "usuario registrado", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Usuarios usuarios, BindingResult result){
        return create(usuarios, result);
    }

    @Operation(summary = "Registrar un nuevo usuario como dietista", description = "Registra un nuevo dietista")
    @ApiResponse(responseCode = "201", description = "admin registrado", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    @PostMapping("/registerAdmin")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody Usuarios usuarios, BindingResult result){
        usuarios.setAdmin(true);
        return create(usuarios, result);
    }




    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo "+ err.getField()+ " "+ err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
