package com.procol.procolombia.auth.dto.Response;

import java.time.LocalDateTime;

public record IngresoResponseDTO(
        Integer idIngreso,
        Integer idUsuario,
        LocalDateTime fechaIngreso
) {}
