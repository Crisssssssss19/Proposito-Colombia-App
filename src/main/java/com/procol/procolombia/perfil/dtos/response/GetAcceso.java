package com.procol.procolombia.perfil.dtos.response;

import java.io.Serializable;

public record GetAcceso(
        Integer idUsuario,
        String telefono,
        String email
) implements Serializable {
}
