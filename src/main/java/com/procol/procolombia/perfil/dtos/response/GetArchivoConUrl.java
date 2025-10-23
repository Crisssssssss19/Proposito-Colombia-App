package com.procol.procolombia.perfil.dtos.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public record GetArchivoConUrl(
        Integer id,
        String nombrePublico,
        String url,
        String tipo,
        String tamanio,
        LocalDateTime fechaSubida
) implements Serializable {
}
