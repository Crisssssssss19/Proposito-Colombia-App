package com.procol.procolombia.perfil.dtos.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public record GetArchivo(
        Integer id,
        String nombre,
        String tipo,
        LocalDateTime fechaSubida
) implements Serializable {
}
