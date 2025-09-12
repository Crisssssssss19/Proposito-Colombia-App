package com.procol.procolombia.perfil.dtos.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        String status,
        int statusCode,
        String message,
        T data,
        LocalDateTime timestamp
) {
    public ApiResponse(String status, int statusCode, String message, T data) {
        this(status, statusCode, message, data, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> success(String message, T data, HttpStatus status) {
        return new ApiResponse<>("success", status.value(), message, data, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> error(String message, T data, HttpStatus status) {
        return new ApiResponse<>("error", status.value(), message, data, LocalDateTime.now());
    }
}
