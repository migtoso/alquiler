package com.alquiler.vehiculos.controller;

import com.alquiler.vehiculos.model.EstadoVehiculo;
import com.alquiler.vehiculos.model.Vehiculo;
import com.alquiler.vehiculos.service.VehiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {

    private final VehiculoService vehiculoService;

    // Obtener todos los vehículos
    @GetMapping
    public ResponseEntity<List<Vehiculo>> getAll() {
        return ResponseEntity.ok(vehiculoService.findAll());
    }

    // Obtener vehículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getById(@PathVariable Long id) {
        return vehiculoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo vehículo
    @PostMapping
    public ResponseEntity<Vehiculo> crear(@Valid @RequestBody Vehiculo vehiculo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.save(vehiculo));
    }

    // Actualizar un vehículo existente
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> update(@PathVariable Long id, @Valid @RequestBody Vehiculo vehiculo) {
        return vehiculoService.update(id, vehiculo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un vehículo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return vehiculoService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // Buscar por marca
    @GetMapping("/buscar/marca/{marca}")
    public ResponseEntity<List<Vehiculo>> findByMarca(@PathVariable String marca) {
        return ResponseEntity.ok(vehiculoService.findByMarca(marca));
    }

    // Buscar por modelo
    @GetMapping("/buscar/modelo/{modelo}")
    public ResponseEntity<List<Vehiculo>> findByModelo(@PathVariable String modelo) {
        return ResponseEntity.ok(vehiculoService.findByModelo(modelo));
    }

    // Buscar por estado
    @GetMapping("/buscar/estado/{estado}")
    public ResponseEntity<List<Vehiculo>> findByEstado(@PathVariable EstadoVehiculo estado) {
        return ResponseEntity.ok(vehiculoService.findByEstado(estado));
    }

    // Actualizar estado del vehículo - llamado internamente por el servicio de operaciones
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Vehiculo> updateEstado(@PathVariable Long id,
                                                  @RequestBody Map<String, String> datos) {
        EstadoVehiculo nuevoEstado = EstadoVehiculo.valueOf(datos.get("estado"));
        return vehiculoService.updateEstado(id, nuevoEstado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
