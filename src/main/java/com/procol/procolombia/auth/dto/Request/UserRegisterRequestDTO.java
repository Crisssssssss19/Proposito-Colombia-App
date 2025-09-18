package com.procol.procolombia.auth.dto.Request;

import com.procol.procolombia.auth.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UserRegisterRequestDTO(
        @NotBlank(message = "El nombre no puede estar vacio")
        String nombresUsuario,

        @NotBlank(message = "El apellido no puede estar vacio")
        String apellidosUsuario,

        @NotNull(message = "El tipo de documento es obligatorio")
        Short tipoDocumentoUsuario,

        @NotBlank(message = "El documento no puede estar vacio")
        String documentoUsuario,

        Integer idUbicacion,

        Short estadoUsuario,

        @NotBlank(message = "El telefono no puede estar vacio")
        @Size(max = 10, message = "El telefono no puede tener mas de 10 caracteres")
        String telefonoUsuario,

        @Email
        @NotBlank(message = "El correo no puede estar vacio")
        String correoAcceso,

        @Size(min = 6, message = "La clave debe tener al menos 6 caracteres")
        @NotBlank(message = "La clave no puede estar vacia")
        String claveAcceso,

        Set<String> roles
) {}
