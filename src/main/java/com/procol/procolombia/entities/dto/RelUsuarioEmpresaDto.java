package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.RelUsuarioEmpresa}
 */
public class RelUsuarioEmpresaDto implements Serializable {
    private final Integer idUsuario;
    private final Integer idEmpresa;
    private final Short permisoRelUsuarioEmpresa;

    public RelUsuarioEmpresaDto(Integer usuarioIdUsuario, Integer empresaIdEmpresa, Short permisoRelUsuarioEmpresa) {
        this.idUsuario = usuarioIdUsuario;
        this.idEmpresa = empresaIdEmpresa;
        this.permisoRelUsuarioEmpresa = permisoRelUsuarioEmpresa;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public Short getPermisoRelUsuarioEmpresa() {
        return permisoRelUsuarioEmpresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelUsuarioEmpresaDto entity = (RelUsuarioEmpresaDto) o;
        return Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.idEmpresa, entity.idEmpresa) &&
                Objects.equals(this.permisoRelUsuarioEmpresa, entity.permisoRelUsuarioEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idEmpresa, permisoRelUsuarioEmpresa);
    }
}