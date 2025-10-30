package com.procol.procolombia.perfil.dtos.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public record GetImagenConUrl(
        Integer id,
        String nombrePublico,
        String nombrePrivado,
        String url,
        boolean favorita,
        String tipo,
        String tamanio,
        LocalDateTime fechaSubida
) implements Serializable {
}
