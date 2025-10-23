package com.procol.procolombia.perfil.dtos.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public record SaveImagenFile(

        @NotNull(message = "El archivo no puede ser nulo")
        MultipartFile file,

        @NotNull(message = "El campo favorita no puede ser nulo")
        boolean favorita

) implements Serializable {
}
