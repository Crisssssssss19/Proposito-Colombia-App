package com.procol.procolombia.perfil.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SaveImagen(

        @NotNull(message = "El ID de usuario no puede ser nulo")
        @Positive(message = "El ID de usuario debe ser un número positivo")
        Integer idUsuario,

        @NotBlank(message = "El nombre público no puede estar vacío")
        @Size(max = 100, message = "El nombre público no puede exceder los 100 caracteres")
        String nombrePublico,

        @NotBlank(message = "El nombre privado no puede estar vacío")
        @Size(max = 100, message = "El nombre privado no puede exceder los 100 caracteres")
        String nombrePrivado,

        @NotBlank(message = "El tipo no puede estar vacío")
        String tipo,

        @NotBlank(message = "El tamaño no puede estar vacío")
        String tamanio,

        @NotNull(message = "El campo favorita no puede ser nulo")
        Short favorita


) implements Serializable {
}
