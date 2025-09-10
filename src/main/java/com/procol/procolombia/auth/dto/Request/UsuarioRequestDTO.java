package com.procol.procolombia.auth.dto.Request;

public record UsuarioRequestDTO(
        Integer idUbicacion,
        Short tipoDocumentoUsuario,
        String documentoUsuario,
        String nombreUsuario,
        String apellidosUsuario,
        Short estadoUsuario
) {}
