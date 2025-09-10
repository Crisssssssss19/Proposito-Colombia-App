package com.procol.procolombia.auth.dto.Response;

public record UbicacionResponseDTO(
        Integer idUbicacion,
        String nombreUbicacion,
        String idDaneUbicacion,
        String longitudUbicacion,
        String latitudUbicacion
) {}
