package com.procol.procolombia.auth.dto.Response;

public record AccesoResponseDTO(
        Integer usuario,
        String correoAcceso,
        String telefonoAcceso,
        String uuidAcceso
) {}
