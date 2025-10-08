package com.procol.procolombia.perfil.dtos.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public record GetImagen(
        Integer id,
        String nombrePublico,
        String nombrePrivado,
        Short favorita,
        String tipo,
        String tamanio,
        LocalDateTime fechaSubida
) implements Serializable {
}
