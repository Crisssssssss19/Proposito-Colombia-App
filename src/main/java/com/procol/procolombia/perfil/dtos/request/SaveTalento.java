package com.procol.procolombia.perfil.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record SaveTalento(

        @NotBlank(message = "Este campo no puede estar vacío")
        String nombre,

        @NotNull
        Short tipo // habilidad = 1, competencia = 2
)  implements Serializable {
}
