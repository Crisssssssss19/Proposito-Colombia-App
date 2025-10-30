package com.procol.procolombia.auth.dto.Response;

public record CorreoVerificacionResponseDTO(
    String idCorreo,
    Short estadoCorreoVerificado) {}
