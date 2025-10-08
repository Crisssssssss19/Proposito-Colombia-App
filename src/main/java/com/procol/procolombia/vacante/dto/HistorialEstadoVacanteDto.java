package com.procol.procolombia.vacante.dto;

import java.time.Instant;

public record HistorialEstadoVacanteDto(
    Integer id,
    Integer idVacante,
    Integer idEstadoVacante,
    Instant fechaHistorialEstadoVacante
    ){}