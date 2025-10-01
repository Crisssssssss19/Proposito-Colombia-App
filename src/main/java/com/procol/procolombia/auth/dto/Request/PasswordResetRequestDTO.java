package com.procol.procolombia.auth.dto.Request;

public record PasswordResetRequestDTO(
        String correoAcceso,
        String newPassword) {
}
