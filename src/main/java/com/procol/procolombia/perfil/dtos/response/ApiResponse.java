package com.procol.procolombia.perfil.dtos.response;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        String status,
        String message,
        T data,
        LocalDateTime timestamp
) {
    public ApiResponse(String status, String message, T data) {
        this(status, message, data, LocalDateTime.now());
    }
}
