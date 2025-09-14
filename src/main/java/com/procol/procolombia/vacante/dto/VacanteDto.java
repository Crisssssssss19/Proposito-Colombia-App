package com.procol.procolombia.vacante.dto;

import com.procol.procolombia.vacante.entities.RelUsuarioEmpresaId;

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
    Set<Integer> palabrasClaveIds,
    Set<Integer> requisitoIds,
    RelUsuarioEmpresaId relUsuarioEmpresaId
){}