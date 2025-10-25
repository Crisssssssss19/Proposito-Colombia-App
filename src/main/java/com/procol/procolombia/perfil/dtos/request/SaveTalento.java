package com.procol.procolombia.perfil.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SaveTalento(

        @NotBlank(message = "Este campo no puede estar vacío")
        @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
        String nombre,

        @NotNull
        Short tipo // habilidad = 1, competencia = 2
)  implements Serializable {
}
