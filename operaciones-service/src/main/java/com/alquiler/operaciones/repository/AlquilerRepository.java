package com.alquiler.operaciones.repository;

import com.alquiler.operaciones.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    // Verifica si existe un alquiler para el mismo vehículo con fechas solapadas
    // Hay solape cuando: fechaInicial del existente <= fechaFinal del nuevo
    //                    Y fechaFinal del existente >= fechaInicial del nuevo
    boolean existsByVehiculoIdAndFechaInicialLessThanEqualAndFechaFinalGreaterThanEqual(
            Long vehiculoId, LocalDate fechaFinal, LocalDate fechaInicial);
}
