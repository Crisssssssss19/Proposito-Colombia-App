package com.procol.procolombia.perfil.dtos.response;

import java.io.Serializable;
import java.util.List;

public record GetUsuario(
        Integer id,
        String nombres,
        String apellidos,
        String documento,
        String tipoDocumento,
        Short estado,
        String telefono,
        List<String> imagenes,
        List<String> archivos
) implements Serializable {
}
