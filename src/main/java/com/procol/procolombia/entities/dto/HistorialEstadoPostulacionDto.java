package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.HistorialEstadoPostulacion}
 */
public class HistorialEstadoPostulacionDto implements Serializable {
    private final Integer idHistorialPostulacion;
    private final Date fechaHistorialPostulacion;
    private final String detalleHistorialPostulacion;
    private final Integer idPostulacion;
    private final Integer idEstadoPostulacion;

    public HistorialEstadoPostulacionDto(Integer idHistorialPostulacion, Date fechaHistorialPostulacion, String detalleHistorialPostulacion, Integer postulacionIdPostulacion, Integer estadoPostulacionIdEstadoPostulacion) {
        this.idHistorialPostulacion = idHistorialPostulacion;
        this.fechaHistorialPostulacion = fechaHistorialPostulacion;
        this.detalleHistorialPostulacion = detalleHistorialPostulacion;
        this.idPostulacion = postulacionIdPostulacion;
        this.idEstadoPostulacion = estadoPostulacionIdEstadoPostulacion;
    }

    public Integer getIdHistorialPostulacion() {
        return idHistorialPostulacion;
    }

    public Date getFechaHistorialPostulacion() {
        return fechaHistorialPostulacion;
    }

    public String getDetalleHistorialPostulacion() {
        return detalleHistorialPostulacion;
    }

    public Integer getIdPostulacion() {
        return idPostulacion;
    }

    public Integer getIdEstadoPostulacion() {
        return idEstadoPostulacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistorialEstadoPostulacionDto entity = (HistorialEstadoPostulacionDto) o;
        return Objects.equals(this.idHistorialPostulacion, entity.idHistorialPostulacion) &&
                Objects.equals(this.fechaHistorialPostulacion, entity.fechaHistorialPostulacion) &&
                Objects.equals(this.detalleHistorialPostulacion, entity.detalleHistorialPostulacion) &&
                Objects.equals(this.idPostulacion, entity.idPostulacion) &&
                Objects.equals(this.idEstadoPostulacion, entity.idEstadoPostulacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHistorialPostulacion, fechaHistorialPostulacion, detalleHistorialPostulacion, idPostulacion, idEstadoPostulacion);
    }
}