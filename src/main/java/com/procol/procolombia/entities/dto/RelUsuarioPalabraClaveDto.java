package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.RelUsuarioPalabraClave}
 */
public class RelUsuarioPalabraClaveDto implements Serializable {
    private final Integer idUsuario;
    private final Integer idPalabraClave;

    public RelUsuarioPalabraClaveDto(Integer usuarioIdUsuario, Integer palabraClaveIdPalabraClave) {
        this.idUsuario = usuarioIdUsuario;
        this.idPalabraClave = palabraClaveIdPalabraClave;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Integer getIdPalabraClave() {
        return idPalabraClave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelUsuarioPalabraClaveDto entity = (RelUsuarioPalabraClaveDto) o;
        return Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.idPalabraClave, entity.idPalabraClave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idPalabraClave);
    }
}