package com.procol.procolombia.perfil.dtos.request;

import java.io.Serializable;

public record SaveAcceso(
        Integer idUsuario,
        String telefonoAcceso,
        String correoAcceso,
        String claveAcceso
) implements Serializable {
}
