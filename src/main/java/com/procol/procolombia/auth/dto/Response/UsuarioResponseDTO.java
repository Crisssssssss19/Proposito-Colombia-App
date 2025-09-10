package com.procol.procolombia.auth.dto.Response;

public record UsuarioResponseDTO(
        Integer idUsuario,
        String nombresUsuario,
        String apellidosUsuario,
        Short estadoUsuario,
        String documentoUsuario,
        String nombreUbicacion
) {
}
