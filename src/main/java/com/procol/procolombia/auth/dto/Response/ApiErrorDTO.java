package com.procol.procolombia.auth.dto.Response;

public record ApiErrorDTO(
        String fechaHora,
        String error,
        String mensaje,
        int codigoEstado
) {}
