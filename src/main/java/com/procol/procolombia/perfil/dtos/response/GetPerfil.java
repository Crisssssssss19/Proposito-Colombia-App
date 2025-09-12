package com.procol.procolombia.perfil.dtos.response;

import java.io.Serializable;
import java.util.List;

public record GetPerfil(
        Integer id,
        String nombres,
        String apellidos,
        String email,
        String telefono,
        String ubicacion,
        List<GetImagenConUrl> imagenes,
        List<GetArchivoConUrl> archivos
) implements Serializable {
}
