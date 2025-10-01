package com.procol.procolombia.auth.dto.Request;

public record LoginRequestDTO(
        String correoAcceso,
        String claveAcceso
) {}
