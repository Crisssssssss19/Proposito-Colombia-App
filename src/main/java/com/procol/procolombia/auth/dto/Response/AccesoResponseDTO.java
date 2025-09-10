package com.procol.procolombia.auth.dto.Response;

public record AccesoResponseDTO(
        Integer idUsuario,
        String correoAcceso,
        String telefonoAcceso,
        String uuidAcceso
) {
}
