package com.procol.procolombia.perfil.dtos.request;

import java.io.Serializable;
import java.util.List;

public record UpdateUsuario(
        Short tipoDocumento,
        String documento,
        String nombres,
        String apellidos,
        Short estado,
        Integer idUbicacion,
        String habilidades,
        String competencias,
        List<String> palabrasClave
) implements Serializable {
}
