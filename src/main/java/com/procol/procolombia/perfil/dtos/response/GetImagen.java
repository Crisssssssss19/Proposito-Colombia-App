package com.procol.procolombia.perfil.dtos.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public record GetImagen(
        Integer id,
        String nombre,
        LocalDateTime fechaSubida
) implements Serializable {
}
