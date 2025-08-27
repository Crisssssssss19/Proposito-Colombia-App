package com.procol.procolombia.entities.dto;

import com.procol.procolombia.entities.TipoEmpresa;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link TipoEmpresa}
 */
public class TipoEmpresaDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(max = 150)
    private final String nombreTipoEmpresa;
    @NotNull
    private final Short estadoTipoEmpresa;
    private final Set<Integer> empresaIds;

    public TipoEmpresaDto(Integer id, String nombreTipoEmpresa, Short estadoTipoEmpresa, Set<Integer> empresaIds) {
        this.id = id;
        this.nombreTipoEmpresa = nombreTipoEmpresa;
        this.estadoTipoEmpresa = estadoTipoEmpresa;
        this.empresaIds = empresaIds;
    }

    public Integer getId() {
        return id;
    }

    public String getNombreTipoEmpresa() {
        return nombreTipoEmpresa;
    }

    public Short getEstadoTipoEmpresa() {
        return estadoTipoEmpresa;
    }

    public Set<Integer> getEmpresaIds() {
        return empresaIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoEmpresaDto entity = (TipoEmpresaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nombreTipoEmpresa, entity.nombreTipoEmpresa) &&
                Objects.equals(this.estadoTipoEmpresa, entity.estadoTipoEmpresa) &&
                Objects.equals(this.empresaIds, entity.empresaIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreTipoEmpresa, estadoTipoEmpresa, empresaIds);
    }
}