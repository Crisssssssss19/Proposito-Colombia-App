package com.procol.procolombia.auth.dto.Request;


import jakarta.validation.constraints.*;
import org.aspectj.bridge.IMessage;

public record AccesoRequestDTO(
        @NotNull(message = "Debe existir un usuario")
        Integer usuario,

        @NotBlank(message = "El correo no puede estar vacio")
        @Email(message = "Debe ser un correo valido")
        String correoAcceso,

        @NotBlank(message = "El telefono no puede estar vacio")
        @Size(max = 10, message = "El telefono no puede tener mas de 10 caracteres")
        @Pattern(regexp = "^[0-9]*$", message = "El telefono solo puede contener numeros")
        String telefonoAcceso,

        @NotBlank(message = "La clave no puede estar vacia")
        @Size(min = 6, message = "La clave debe tener al menos 6 caracteres")
        String claveAcceso,

        @NotBlank(message = "El uuid no puede estar vacio")
        String uuidAcceso
) {}
