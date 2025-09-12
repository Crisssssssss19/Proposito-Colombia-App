package com.procol.procolombia.perfil.dtos.request;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public record SaveImagenFile(
        MultipartFile file,
        boolean favorita
) implements Serializable {
}
