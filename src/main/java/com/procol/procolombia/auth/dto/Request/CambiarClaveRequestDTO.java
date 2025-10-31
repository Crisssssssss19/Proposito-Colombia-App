package com.procol.procolombia.auth.dto.Request;

public record CambiarClaveRequestDTO(
        String claveActual,
        String nuevaClave,
        String confirmarClave
) {
}
