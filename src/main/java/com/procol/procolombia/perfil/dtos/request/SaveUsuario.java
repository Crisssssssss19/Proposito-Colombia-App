package com.procol.procolombia.perfil.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SaveUsuario(

        @NotBlank(message = "El tipo de documento es obligatorio")
        Short tipoDocumento,

        @NotBlank(message = "El documento es obligatorio")
        @Size(min = 5, max = 20, message = "El documento debe tener entre 5 y 20 caracteres")
        String documento,

        @NotBlank(message = "Los nombres son obligatorio")
        @Size(min = 2, max = 50, message = "Los nombre deben tener entre 6 y 50 caracteres")
        String nombres,

        @NotBlank(message = "Los Apellidos son obligatorio")
        @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
        String apellidos,

        @NotNull(message = "El estado es obligatorio")
        Short estado,

        @NotNull(message = "La ubicacion es obligatoria")
        Integer idUbicacion,

        @NotBlank(message = "El teléfono es obligatorio")
        @Pattern(regexp = "\\d{7,15}", message = "El teléfono debe contener solo números y tener entre 7 y 15 dígitos")
        String telefono

) implements Serializable {}
