package com.procol.procolombia.auth.dto.Request;

import com.procol.procolombia.auth.entities.Ubicacione;

public record UsuarioRequestDTO(
        Ubicacione idUbicacion,
        Short tipoDocumentoUsuario,
        String documentoUsuario,
        String nombresUsuario,
        String apellidosUsuario,
        Short estadoUsuario
) {}
