package com.procol.procolombia.auth.dto.Request;

public record UserRegisterRequestDTO(
        String nombresUsuario,
        String apellidosUsuario,
        String correoAcceso,
        String claveAcceso
) {}
