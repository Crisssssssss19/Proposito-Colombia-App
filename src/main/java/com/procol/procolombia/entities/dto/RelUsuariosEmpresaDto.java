package com.procol.procolombia.entities.dto;

import com.procol.procolombia.entities.RelUsuariosEmpresaId;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.procol.procolombia.entities.RelUsuariosEmpresa}
 */
public class RelUsuariosEmpresaDto implements Serializable {
    private final RelUsuariosEmpresaId id;
    private final Integer idUsuario;
    private final Integer idEmpresa;
    @NotNull
    private final Short permisoRelUsuarioEmpresa;
    private final Set<Integer> vacanteIds;

    public RelUsuariosEmpresaDto(RelUsuariosEmpresaId id, Integer idUsuarioId, Integer idEmpresaId, Short permisoRelUsuarioEmpresa, Set<Integer> vacanteIds) {
        this.id = id;
        this.idUsuario = idUsuarioId;
        this.idEmpresa = idEmpresaId;
        this.permisoRelUsuarioEmpresa = permisoRelUsuarioEmpresa;
        this.vacanteIds = vacanteIds;
    }

    public RelUsuariosEmpresaId getId() {
        return id;
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

    public Set<Integer> getVacanteIds() {
        return vacanteIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelUsuariosEmpresaDto entity = (RelUsuariosEmpresaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.idEmpresa, entity.idEmpresa) &&
                Objects.equals(this.permisoRelUsuarioEmpresa, entity.permisoRelUsuarioEmpresa) &&
                Objects.equals(this.vacanteIds, entity.vacanteIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUsuario, idEmpresa, permisoRelUsuarioEmpresa, vacanteIds);
    }
}