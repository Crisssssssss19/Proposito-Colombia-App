package com.procol.procolombia.perfil.dtos.response;

import java.io.Serializable;

public record GetPalabraClave(
        Integer id,
        String textoPalabraClave
) implements Serializable {
}
