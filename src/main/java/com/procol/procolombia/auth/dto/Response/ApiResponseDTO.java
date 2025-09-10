package com.procol.procolombia.auth.dto.Response;

public record ApiResponseDTO<T>(
        int codigoEstado,
        String mensaje,
        T datos,
        String fechaHora
) {}
