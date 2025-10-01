package com.procol.procolombia.auth.dto.Response;

import com.procol.procolombia.auth.entities.Ubicacione;

public record UsuarioResponseDTO(
        Integer idUsuario,
        String nombresUsuario,
        String apellidosUsuario,
        Short estadoUsuario,
        String documentoUsuario,
        UbicacionResponseDTO ubicacionResponseDTO
) {}
