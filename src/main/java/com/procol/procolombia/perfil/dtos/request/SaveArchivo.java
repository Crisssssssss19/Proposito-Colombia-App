package com.procol.procolombia.perfil.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SaveArchivo(

        @NotNull(message = "El ID de usuario no puede ser nulo")
        @Positive(message = "El ID de usuario debe ser un número positivo")
        Integer idUsuario,


        @NotBlank(message = "El nombre público no puede estar vacío")
        @Size(max = 100, message = "El nombre público no puede exceder los 100 caracteres")
        String nombrePublico,

        @NotBlank(message = "El nombre del archivo no puede estar vacío")
        @Size(max = 100, message = "El nombre del archivo no puede exceder los 100 caracteres")
        String nombreArchivo,

        @NotBlank(message = "El tipo no puede estar vacío")
        String tipo,

        @NotBlank(message = "El tamaño no puede estar vacío")
        String tamanio,

        @NotNull(message = "El grupo no puede ser nulo")
        Short grupo

) implements Serializable {
}
