package com.procol.procolombia.perfil.dtos.request;

import java.io.Serializable;

public record SaveArchivo(
        Integer idUsuario,
        String nombrePublico,
        String nombreArchivo,
        String tipo,
        String tamanio,
        Short grupo
) implements Serializable {
}
