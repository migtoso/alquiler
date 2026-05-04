package com.alquiler.operaciones.repository;

import com.alquiler.operaciones.model.Alquiler;
import com.alquiler.operaciones.model.EstadoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    List<Alquiler> findByEstadoSolicitud(EstadoSolicitud estado);

    // Utilizado por el planificador: busca alquileres activos cuya fecha final sea anterior a hoy
    List<Alquiler> findByEstadoSolicitudAndFechaFinalBefore(EstadoSolicitud estado, LocalDate fecha);
}
