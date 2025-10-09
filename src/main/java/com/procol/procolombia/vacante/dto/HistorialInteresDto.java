package com.procol.procolombia.vacante.dto;

import java.time.Instant;

public record HistorialInteresDto(
        int idHistorialInteres,
        short tipoInteres,
        Instant fechaInteres,
        InteresIdDto idInteres
){}
