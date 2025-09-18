package com.procol.procolombia.auth.dto.Request;

import com.procol.procolombia.auth.entities.Ubicacione;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
        Integer idUbicacion,
        @NotNull(message = "El tipo de documento es obligatorio")
        Short tipoDocumentoUsuario,
        @NotBlank(message = "El documento no puede estar vacio")
        @Size(max = 10, message = "El documento no puede tener mas de 10 caracteres")
        String documentoUsuario,
        @NotBlank(message = "Los nombres no pueden estar vacios")
        String nombresUsuario,
        @NotBlank(message = "Los apellidos no pueden estar vacios")
        String apellidosUsuario,
        Short estadoUsuario
) {}
