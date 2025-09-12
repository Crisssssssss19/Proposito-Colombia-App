package com.procol.procolombia.auth.dto.Request;

import com.procol.procolombia.auth.entities.Role;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record UserRegisterRequestDTO(
        @NotBlank Integer idUsuarioRef,
        @NotBlank String nombresUsuario,
        @NotBlank String apellidosUsuario,
        @NotBlank String correoAcceso,
        @NotBlank String claveAcceso,
        Set<String> roles
) {}
