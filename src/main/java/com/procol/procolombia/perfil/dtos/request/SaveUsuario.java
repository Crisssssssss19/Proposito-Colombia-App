package com.procol.procolombia.perfil.dtos.request;

import java.io.Serializable;

public record SaveUsuario(

        Short tipoDocumento,
        String documento,
        String nombres,
        String apellidos,
        Short estado,
        Integer idUbicacion,
        String telefono
) implements Serializable {}
