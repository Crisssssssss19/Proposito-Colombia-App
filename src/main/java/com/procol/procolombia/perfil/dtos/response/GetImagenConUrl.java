package com.procol.procolombia.perfil.dtos.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public record GetImagenConUrl(
        Integer id,
        String nombrePublico,
        String url,
        boolean favorita,
        LocalDateTime fechaSubida
) implements Serializable {
}
