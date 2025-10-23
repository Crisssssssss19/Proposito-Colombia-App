package com.procol.procolombia.postulacion.dto;

import com.procol.procolombia.postulacion.entities.RelUsuarioPalabraclaveId;
import com.procol.procolombia.postulacion.entities.RelUsuarioPalabraclave;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link RelUsuarioPalabraclave}
 */
public class RelUsuarioPalabraclaveDto implements Serializable {
    private RelUsuarioPalabraclaveId id;
    private Integer idUsuario;
    private Integer idPalabraClave;

    public RelUsuarioPalabraclaveDto() {
    }

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

    public void setId(RelUsuarioPalabraclaveId id) {
        this.id = id;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdPalabraClave(Integer idPalabraClave) {
        this.idPalabraClave = idPalabraClave;
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