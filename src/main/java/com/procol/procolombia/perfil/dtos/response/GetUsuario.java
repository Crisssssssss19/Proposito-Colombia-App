package com.procol.procolombia.perfil.dtos.response;

import java.io.Serializable;

public record GetUsuario(
        Integer id,
        String nombresUsuario,
        String apellidosUsuario,
        Short estado,
        Integer idUbicacion
) implements Serializable {
}
