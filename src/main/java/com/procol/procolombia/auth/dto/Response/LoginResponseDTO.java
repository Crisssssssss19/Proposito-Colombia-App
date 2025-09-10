package com.procol.procolombia.auth.dto.Response;

public record LoginResponseDTO(
        String tokenApp,
        String fotoApp,
        Long expiraEn
) {}
