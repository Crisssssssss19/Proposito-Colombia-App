package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.HistorialEstadoVacante}
 */
public class HistorialEstadoVacanteDto implements Serializable {
    private final Integer idHistorialEstadoVacante;
    private final Date fechaHistorialEstadoVacante;
    private final Integer idVacante;
    private final Integer idEstadoVacante;

    public HistorialEstadoVacanteDto(Integer idHistorialEstadoVacante, Date fechaHistorialEstadoVacante, Integer vacanteIdVacante, Integer estadoVacanteIdEstadoVacante) {
        this.idHistorialEstadoVacante = idHistorialEstadoVacante;
        this.fechaHistorialEstadoVacante = fechaHistorialEstadoVacante;
        this.idVacante = vacanteIdVacante;
        this.idEstadoVacante = estadoVacanteIdEstadoVacante;
    }

    public Integer getIdHistorialEstadoVacante() {
        return idHistorialEstadoVacante;
    }

    public Date getFechaHistorialEstadoVacante() {
        return fechaHistorialEstadoVacante;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public Integer getIdEstadoVacante() {
        return idEstadoVacante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistorialEstadoVacanteDto entity = (HistorialEstadoVacanteDto) o;
        return Objects.equals(this.idHistorialEstadoVacante, entity.idHistorialEstadoVacante) &&
                Objects.equals(this.fechaHistorialEstadoVacante, entity.fechaHistorialEstadoVacante) &&
                Objects.equals(this.idVacante, entity.idVacante) &&
                Objects.equals(this.idEstadoVacante, entity.idEstadoVacante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHistorialEstadoVacante, fechaHistorialEstadoVacante, idVacante, idEstadoVacante);
    }
}