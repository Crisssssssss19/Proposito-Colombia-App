package com.procol.procolombia.vacante.dto;

import com.procol.procolombia.vacante.entities.RelUsuariosEmpresaId;
import com.procol.procolombia.vacante.entities.Empresa;
import com.procol.procolombia.vacante.entities.Interese;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Empresa}
 */
public class EmpresaDto implements Serializable {
    private final Integer id;
    private final Integer idTipoEmpresa;
    @NotNull
    @Size(max = 200)
    private final String nombreEmpresa;
    @NotNull
    @Size(max = 200)
    private final String direccionEmpresa;
    @NotNull
    @Size(max = 200)
    private final String telefonoEmpresa;
    private final Set<IntereseId> intereses;
    private final Set<RelUsuariosEmpresaId> relUsuariosEmpresaIds;

    public EmpresaDto(Integer id, Integer idTipoEmpresaId, String nombreEmpresa, String direccionEmpresa, String telefonoEmpresa, Set<IntereseId> intereses, Set<RelUsuariosEmpresaId> relUsuariosEmpresaIds) {
        this.id = id;
        this.idTipoEmpresa = idTipoEmpresaId;
        this.nombreEmpresa = nombreEmpresa;
        this.direccionEmpresa = direccionEmpresa;
        this.telefonoEmpresa = telefonoEmpresa;
        this.intereses = intereses;
        this.relUsuariosEmpresaIds = relUsuariosEmpresaIds;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdTipoEmpresa() {
        return idTipoEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public Set<IntereseId> getIntereses() {
        return intereses;
    }

    public Set<RelUsuariosEmpresaId> getRelUsuariosEmpresaIds() {
        return relUsuariosEmpresaIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresaDto entity = (EmpresaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idTipoEmpresa, entity.idTipoEmpresa) &&
                Objects.equals(this.nombreEmpresa, entity.nombreEmpresa) &&
                Objects.equals(this.direccionEmpresa, entity.direccionEmpresa) &&
                Objects.equals(this.telefonoEmpresa, entity.telefonoEmpresa) &&
                Objects.equals(this.intereses, entity.intereses) &&
                Objects.equals(this.relUsuariosEmpresaIds, entity.relUsuariosEmpresaIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idTipoEmpresa, nombreEmpresa, direccionEmpresa, telefonoEmpresa, intereses, relUsuariosEmpresaIds);
    }

    /**
     * DTO for {@link Interese}
     */
    public static class IntereseId implements Serializable {
        private final com.procol.procolombia.vacante.entities.IntereseId id;
        private final Short tipoInteres;

        public IntereseId(com.procol.procolombia.vacante.entities.IntereseId id, Short tipoInteres) {
            this.id = id;
            this.tipoInteres = tipoInteres;
        }

        public com.procol.procolombia.vacante.entities.IntereseId getId() {
            return id;
        }

        public Short getTipoInteres() {
            return tipoInteres;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IntereseId entity = (IntereseId) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.tipoInteres, entity.tipoInteres);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, tipoInteres);
        }
    }
}