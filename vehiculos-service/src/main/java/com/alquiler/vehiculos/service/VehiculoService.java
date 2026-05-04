package com.alquiler.vehiculos.service;

import com.alquiler.vehiculos.model.EstadoVehiculo;
import com.alquiler.vehiculos.model.Vehiculo;
import com.alquiler.vehiculos.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public List<Vehiculo> findAll() {
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> findById(Long id) {
        return vehiculoRepository.findById(id);
    }

    public Vehiculo save(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public Optional<Vehiculo> update(Long id, Vehiculo vehiculoActualizado) {
        return vehiculoRepository.findById(id).map(vehiculo -> {
            vehiculo.setMarca(vehiculoActualizado.getMarca());
            vehiculo.setModelo(vehiculoActualizado.getModelo());
            vehiculo.setAnio(vehiculoActualizado.getAnio());
            vehiculo.setPlaca(vehiculoActualizado.getPlaca());
            vehiculo.setEstado(vehiculoActualizado.getEstado());
            return vehiculoRepository.save(vehiculo);
        });
    }

    public boolean deleteById(Long id) {
        if (vehiculoRepository.existsById(id)) {
            vehiculoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Vehiculo> findByMarca(String marca) {
        return vehiculoRepository.findByMarcaIgnoreCase(marca);
    }

    public List<Vehiculo> findByModelo(String modelo) {
        return vehiculoRepository.findByModeloIgnoreCase(modelo);
    }

    public List<Vehiculo> findByEstado(EstadoVehiculo estado) {
        return vehiculoRepository.findByEstado(estado);
    }

    public Optional<Vehiculo> updateEstado(Long id, EstadoVehiculo nuevoEstado) {
        return vehiculoRepository.findById(id).map(vehiculo -> {
            vehiculo.setEstado(nuevoEstado);
            return vehiculoRepository.save(vehiculo);
        });
    }
}
