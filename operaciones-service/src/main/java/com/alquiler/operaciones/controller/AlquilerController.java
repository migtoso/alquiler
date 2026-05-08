package com.alquiler.operaciones.controller;

import com.alquiler.operaciones.model.Alquiler;
import com.alquiler.operaciones.service.AlquilerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/operaciones")
@RequiredArgsConstructor
public class AlquilerController {

    private final AlquilerService alquilerService;

    // Obtener todos los alquileres
    @GetMapping
    public ResponseEntity<List<Alquiler>> getAll() {
        return ResponseEntity.ok(alquilerService.findAll());
    }

    // Obtener alquiler por ID
    @GetMapping("/{id}")
    public ResponseEntity<Alquiler> getById(@PathVariable Long id) {
        return alquilerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Registrar un nuevo alquiler
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Alquiler alquiler) {
        try {
            Alquiler guardado = alquilerService.registrarAlquiler(alquiler);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}

