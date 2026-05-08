package com.alquiler.operaciones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "alquileres")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El usuario es obligatorio")
    @Column(nullable = false)
    private String usuario;

    @NotNull(message = "El id del vehículo es obligatorio")
    @Column(name = "vehiculo_id", nullable = false)
    private Long vehiculoId;

    @NotNull(message = "La fecha inicial es obligatoria")
    @Column(name = "fecha_inicial", nullable = false)
    private LocalDate fechaInicial;

    @NotNull(message = "La fecha final es obligatoria")
    @Column(name = "fecha_final", nullable = false)
    private LocalDate fechaFinal;
}
