package com.procol.procolombia.perfil.dtos.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public record GetArchivo(
        Integer id,
        String nombrePublico,
        String nombrePrivado,
        String tipo,
        String tamanio,
        LocalDateTime fechaSubida
) implements Serializable {
}
