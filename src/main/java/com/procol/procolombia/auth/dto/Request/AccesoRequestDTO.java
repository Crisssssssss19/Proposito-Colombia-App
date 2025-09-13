package com.procol.procolombia.auth.dto.Request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccesoRequestDTO(
        Integer usuario,
        String correoAcceso,
        String claveAcceso,
        String telefonoAcceso
) {}
