package com.procol.procolombia.perfil.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SaveAcceso(

        @NotNull(message = "El ID de usuario no puede ser nulo")
        Integer idUsuario,

        @NotBlank(message = "El teléfono de acceso no puede estar vacío")
        @Size(max = 15, message = "El teléfono de acceso no puede exceder los 15 caracteres")
        String telefonoAcceso,


        @NotBlank(message = "El correo de acceso no puede estar vacío")
        @Email(message = "El correo de acceso debe ser válido")
        String correoAcceso,

        @NotBlank(message = "La clave de acceso no puede estar vacía")
        @Size(min = 8, max = 20, message = "La clave de acceso debe tener entre 8 y 20 caracteres")
        String claveAcceso

) implements Serializable {
}
