package com.procol.procolombia.perfil.dtos.response;

public record GetUsuarioTalento(
        Integer id,
        String nombreTalento,
        Short tipo, // 1 = habilidad, 2 = competencia
        Integer nivelDominio
) {
}
