package com.procol.procolombia.entities.dto;

import com.procol.procolombia.entities.RelUsuarioPalabraclaveId;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.RelUsuarioPalabraclave}
 */
public class RelUsuarioPalabraclaveDto implements Serializable {
    private final RelUsuarioPalabraclaveId id;
    private final Integer idUsuario;
    private final Integer idPalabraClave;

    public RelUsuarioPalabraclaveDto(RelUsuarioPalabraclaveId id, Integer idUsuarioId, Integer idPalabraClaveId) {
        this.id = id;
        this.idUsuario = idUsuarioId;
        this.idPalabraClave = idPalabraClaveId;
    }

    public RelUsuarioPalabraclaveId getId() {
        return id;
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
        RelUsuarioPalabraclaveDto entity = (RelUsuarioPalabraclaveDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.idPalabraClave, entity.idPalabraClave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUsuario, idPalabraClave);
    }
}