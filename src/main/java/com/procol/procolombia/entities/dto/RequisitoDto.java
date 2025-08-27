package com.procol.procolombia.entities.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Requisito}
 */
public class RequisitoDto implements Serializable {
    private final Integer id;
    private final Integer idVacante;
    @NotNull
    @Size(max = 200)
    private final String tituloRequisito;
    @NotNull
    private final String detalleRequisito;
    @NotNull
    private final Short ordenRequisito;

    public RequisitoDto(Integer id, Integer idVacanteId, String tituloRequisito, String detalleRequisito, Short ordenRequisito) {
        this.id = id;
        this.idVacante = idVacanteId;
        this.tituloRequisito = tituloRequisito;
        this.detalleRequisito = detalleRequisito;
        this.ordenRequisito = ordenRequisito;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public String getTituloRequisito() {
        return tituloRequisito;
    }

    public String getDetalleRequisito() {
        return detalleRequisito;
    }

    public Short getOrdenRequisito() {
        return ordenRequisito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequisitoDto entity = (RequisitoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idVacante, entity.idVacante) &&
                Objects.equals(this.tituloRequisito, entity.tituloRequisito) &&
                Objects.equals(this.detalleRequisito, entity.detalleRequisito) &&
                Objects.equals(this.ordenRequisito, entity.ordenRequisito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idVacante, tituloRequisito, detalleRequisito, ordenRequisito);
    }
}