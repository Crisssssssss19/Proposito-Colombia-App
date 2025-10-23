package com.procol.procolombia.perfil.dtos.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public record SaveArchivoFile(

        @NotNull(message = "El archivo no puede ser nulo")
        MultipartFile file

) implements Serializable {
}
