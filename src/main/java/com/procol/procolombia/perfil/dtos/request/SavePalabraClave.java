package com.procol.procolombia.perfil.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record SavePalabraClave(

        @NotBlank(message = "Este campo no puede estar vacío")
        String textoPalabraClave

) implements Serializable {
}
