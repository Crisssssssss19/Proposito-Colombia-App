package com.procol.procolombia.entities.dto;

import com.procol.procolombia.entities.TipoEmpresa;

import java.io.Serializable;
import java.lang.Integer;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link TipoEmpresa}
 */
public class TipoEmpresaDto implements Serializable {
    private final java.lang.Integer idTipoEmpresa;
    private final String nombreTipoEmpresa;
    private final Short estadoTipoEmpresa;
    private final List<Integer> idEmpresas;

    public TipoEmpresaDto(java.lang.Integer idTipoEmpresa, String nombreTipoEmpresa, Short estadoTipoEmpresa, List<java.lang.Integer> empresaIdEmpresas) {
        this.idTipoEmpresa = idTipoEmpresa;
        this.nombreTipoEmpresa = nombreTipoEmpresa;
        this.estadoTipoEmpresa = estadoTipoEmpresa;
        this.idEmpresas = empresaIdEmpresas;
    }

    public java.lang.Integer getIdTipoEmpresa() {
        return idTipoEmpresa;
    }

    public String getNombreTipoEmpresa() {
        return nombreTipoEmpresa;
    }

    public Short getEstadoTipoEmpresa() {
        return estadoTipoEmpresa;
    }

    public List<Integer> getIdEmpresas() {
        return idEmpresas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoEmpresaDto entity = (TipoEmpresaDto) o;
        return Objects.equals(this.idTipoEmpresa, entity.idTipoEmpresa) &&
                Objects.equals(this.nombreTipoEmpresa, entity.nombreTipoEmpresa) &&
                Objects.equals(this.estadoTipoEmpresa, entity.estadoTipoEmpresa) &&
                Objects.equals(this.idEmpresas, entity.idEmpresas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoEmpresa, nombreTipoEmpresa, estadoTipoEmpresa, idEmpresas);
    }
}