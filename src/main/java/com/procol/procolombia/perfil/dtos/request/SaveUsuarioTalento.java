package com.procol.procolombia.perfil.dtos.request;

import java.io.Serializable;

public record SaveUsuarioTalento(
        Integer idTalento,
        Integer nivelDominio
) implements Serializable {
}
