package com.alquiler.operaciones.service;

import com.alquiler.operaciones.client.VehiculosClient;
import com.alquiler.operaciones.model.Alquiler;
import com.alquiler.operaciones.model.EstadoSolicitud;
import com.alquiler.operaciones.repository.AlquilerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
     * Valida que el vehículo exista y esté en estado DISPONIBLE antes de guardar.
     */
    public Alquiler registrarAlquiler(Alquiler alquiler) {
        Map vehiculo = vehiculosClient.getVehiculo(alquiler.getVehiculoId());
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo con id " + alquiler.getVehiculoId() + " no existe.");
        }
        String estado = (String) vehiculo.get("estado");
        if (!"DISPONIBLE".equals(estado)) {
            throw new IllegalStateException("El vehículo con id " + alquiler.getVehiculoId() + " no está disponible.");
        }

        alquiler.setEstadoSolicitud(EstadoSolicitud.ACTIVO);
        Alquiler guardado = alquilerRepository.save(alquiler);

        // Marcar el vehículo como NO_DISPONIBLE
        vehiculosClient.updateEstado(alquiler.getVehiculoId(), "NO_DISPONIBLE");

        return guardado;
    }

    /**
     * Cancela un alquiler activo o pendiente y libera el vehículo.
     */
    public Optional<Alquiler> cancelarAlquiler(Long id) {
        return alquilerRepository.findById(id).map(alquiler -> {
            if (alquiler.getEstadoSolicitud() == EstadoSolicitud.CANCELADO
                    || alquiler.getEstadoSolicitud() == EstadoSolicitud.FINALIZADO) {
                throw new IllegalStateException("La solicitud ya está " + alquiler.getEstadoSolicitud());
            }
            alquiler.setEstadoSolicitud(EstadoSolicitud.CANCELADO);
            Alquiler guardado = alquilerRepository.save(alquiler);

            // Liberar el vehículo
            vehiculosClient.updateEstado(alquiler.getVehiculoId(), "DISPONIBLE");

            return guardado;
        });
    }

    /**
     * Tarea programada: se ejecuta cada minuto.
     * Busca todos los alquileres en estado ACTIVO cuya fecha_final sea anterior a hoy y los marca como FINALIZADO.
     * También actualiza el estado del vehículo a DISPONIBLE en el microservicio de vehículos.
     */
    @Scheduled(fixedDelay = 60000)
    public void finalizarAlquileresVencidos() {
        List<Alquiler> vencidos = alquilerRepository
                .findByEstadoSolicitudAndFechaFinalBefore(EstadoSolicitud.ACTIVO, LocalDate.now());

        for (Alquiler alquiler : vencidos) {
            alquiler.setEstadoSolicitud(EstadoSolicitud.FINALIZADO);
            alquilerRepository.save(alquiler);
            vehiculosClient.updateEstado(alquiler.getVehiculoId(), "DISPONIBLE");
        }
    }
}
