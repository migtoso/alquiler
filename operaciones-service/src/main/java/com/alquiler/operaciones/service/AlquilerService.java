package com.alquiler.operaciones.service;

import com.alquiler.operaciones.client.VehiculosClient;
import com.alquiler.operaciones.model.Alquiler;
import com.alquiler.operaciones.repository.AlquilerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlquilerService {

    private final AlquilerRepository alquilerRepository;
    private final VehiculosClient vehiculosClient;

    public List<Alquiler> findAll() {
        return alquilerRepository.findAll();
    }

    public Optional<Alquiler> findById(Long id) {
        return alquilerRepository.findById(id);
    }

    /**
     * Registra una solicitud de alquiler.
     * Valida que el vehículo exista, esté DISPONIBLE y no tenga alquileres solapados en el rango de fechas.
     */
    public Alquiler registrarAlquiler(Alquiler alquiler) {
        // 1. Verificar que el vehículo existe
        Map vehiculo = vehiculosClient.getVehiculo(alquiler.getVehiculoId());
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo con id " + alquiler.getVehiculoId() + " no existe.");
        }

        // 2. Verificar que el vehículo está DISPONIBLE (no en taller ni bloqueado manualmente)
        String estado = (String) vehiculo.get("estado");
        if (!"DISPONIBLE".equals(estado)) {
            throw new IllegalStateException("El vehículo con id " + alquiler.getVehiculoId() + " no está disponible.");
        }

        // 3. Verificar que no hay alquileres solapados para ese vehículo en el rango de fechas solicitado
        boolean solapado = alquilerRepository
                .existsByVehiculoIdAndFechaInicialLessThanEqualAndFechaFinalGreaterThanEqual(
                        alquiler.getVehiculoId(),
                        alquiler.getFechaFinal(),
                        alquiler.getFechaInicial());
        if (solapado) {
            throw new IllegalStateException(
                    "El vehículo ya tiene un alquiler registrado en ese rango de fechas.");
        }

        return alquilerRepository.save(alquiler);
    }
}

