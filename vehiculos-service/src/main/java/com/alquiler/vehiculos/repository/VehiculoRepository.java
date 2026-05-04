package com.alquiler.vehiculos.repository;

import com.alquiler.vehiculos.model.EstadoVehiculo;
import com.alquiler.vehiculos.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    List<Vehiculo> findByMarcaIgnoreCase(String marca);

    List<Vehiculo> findByModeloIgnoreCase(String modelo);

    List<Vehiculo> findByEstado(EstadoVehiculo estado);
}
