package com.procol.procolombia.vacante.dto;

import com.procol.procolombia.vacante.entities.InteresId;

public record InteresDto (
    InteresId id,
    Integer idEmpresa,
    Integer idUsuario,
    Short tipoInteres
){}