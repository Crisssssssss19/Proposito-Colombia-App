package com.procol.procolombia.perfil.dtos.response;

import java.io.Serializable;

public record GetTalento(
        Integer id,
        String nombre,
        Short tipo
) implements Serializable {
}
