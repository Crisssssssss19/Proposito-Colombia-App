package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Requisito}
 */
public class RequisitoDto implements Serializable {
    private final Integer idRequisito;
    private final String tituloRequisito;
    private final String detalleRequisito;
    private final Short ordenRequisito;
    private final Integer idVacante;

    public RequisitoDto(Integer idRequisito, String tituloRequisito, String detalleRequisito, Short ordenRequisito, Integer vacanteIdVacante) {
        this.idRequisito = idRequisito;
        this.tituloRequisito = tituloRequisito;
        this.detalleRequisito = detalleRequisito;
        this.ordenRequisito = ordenRequisito;
        this.idVacante = vacanteIdVacante;
    }

    public Integer getIdRequisito() {
        return idRequisito;
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

    public Integer getIdVacante() {
        return idVacante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequisitoDto entity = (RequisitoDto) o;
        return Objects.equals(this.idRequisito, entity.idRequisito) &&
                Objects.equals(this.tituloRequisito, entity.tituloRequisito) &&
                Objects.equals(this.detalleRequisito, entity.detalleRequisito) &&
                Objects.equals(this.ordenRequisito, entity.ordenRequisito) &&
                Objects.equals(this.idVacante, entity.idVacante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRequisito, tituloRequisito, detalleRequisito, ordenRequisito, idVacante);
    }
}