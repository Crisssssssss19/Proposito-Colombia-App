package com.procol.procolombia.vacante.dto;

import java.time.Instant;
import java.util.Set;

public record VacanteDto (
    Integer id,
    Integer idUbicacion,
    String tituloVacante,
    String detalleVacante,
    Instant fechaInicioVacante,
    Instant fechaFinVacante,
    Short estadoVacante,
    Integer anuncioId,
    Set<Integer> historialEstadosVacanteIds,
    Set<Integer> postulacionIds,
    Set<Integer> palabrasClaveIds,
    Set<Integer> requisitoIds
){}