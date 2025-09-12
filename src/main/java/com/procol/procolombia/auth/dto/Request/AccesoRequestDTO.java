package com.procol.procolombia.auth.dto.Request;

public record AccesoRequestDTO(
        Integer usuario,
        String correoAcceso,
        String claveAcceso,
        String telefonoAcceso
) {}
