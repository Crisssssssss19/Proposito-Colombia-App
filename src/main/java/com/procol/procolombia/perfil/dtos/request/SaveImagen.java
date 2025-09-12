package com.procol.procolombia.perfil.dtos.request;

import java.io.Serializable;

public record SaveImagen(
        Integer idUsuario,
        String nombrePublico,
        String nombrePrivado,
        String tipo,
        String tamanio,
        Short favorita
) implements Serializable {
}
