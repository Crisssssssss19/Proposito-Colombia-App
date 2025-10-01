package com.procol.procolombia.auth.dto.Response;

public record UbicacionResponseDTO(
        Integer idUbicacion,
        Integer idPadreUbicacion,
        String nombreUbicacion,
        String idDaneUbicacion,
        String longitudUbicacion,
        String latitudUbicacion
) {}
