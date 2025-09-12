package com.procol.procolombia.perfil.dtos.response;

import java.io.Serializable;

public record GetAcceso(
        Integer idUsuario,
        String telefonoAcceso,
        String correoAcceso
) implements Serializable {
}
