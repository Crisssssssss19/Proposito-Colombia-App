package com.procol.procolombia.auth.dto.Request;

public record UsuarioRequestDTO(
        Integer idUbicacion,
        Short tipoDocumentoUsuario,
        String documentoUsuario,
        String nombresUsuario,
        String apellidosUsuario,
        Short estadoUsuario
) {}
