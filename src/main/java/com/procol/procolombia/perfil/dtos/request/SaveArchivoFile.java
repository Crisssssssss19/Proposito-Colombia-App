package com.procol.procolombia.perfil.dtos.request;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public record SaveArchivoFile(
        MultipartFile file
) implements Serializable {
}
