package com.procol.procolombia.auth.dto.Request;

public record AccesoRequestDTO(
        Integer idUsuario,
        String correoAcceso,
        String claveAcceso,
        String telefonoAcceso
) {}
