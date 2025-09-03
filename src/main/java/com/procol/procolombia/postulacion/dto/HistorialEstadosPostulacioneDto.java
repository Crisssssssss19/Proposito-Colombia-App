package com.procol.procolombia.postulacion.dto;

import com.procol.procolombia.postulacion.entities.HistorialEstadosPostulacione;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link HistorialEstadosPostulacione}
 */
public class HistorialEstadosPostulacioneDto implements Serializable {
    private final Integer id;
    private final Integer idPostulacionId;
    private final Integer idEstadoPostulacion;
    @NotNull
    private final Instant fechaHistorialPostulacion;
    @NotNull
    private final String detalleHistorialPostulacion;

    public HistorialEstadosPostulacioneDto(Integer id, Integer idPostulacionId, Integer idEstadoPostulacionId, Instant fechaHistorialPostulacion, String detalleHistorialPostulacion) {
        this.id = id;
        this.idPostulacionId = idPostulacionId;
        this.idEstadoPostulacion = idEstadoPostulacionId;
        this.fechaHistorialPostulacion = fechaHistorialPostulacion;
        this.detalleHistorialPostulacion = detalleHistorialPostulacion;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdPostulacionId() {
        return idPostulacionId;
    }

    public Integer getIdEstadoPostulacion() {
        return idEstadoPostulacion;
    }

    public Instant getFechaHistorialPostulacion() {
        return fechaHistorialPostulacion;
    }

    public String getDetalleHistorialPostulacion() {
        return detalleHistorialPostulacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistorialEstadosPostulacioneDto entity = (HistorialEstadosPostulacioneDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idPostulacionId, entity.idPostulacionId) &&
                Objects.equals(this.idEstadoPostulacion, entity.idEstadoPostulacion) &&
                Objects.equals(this.fechaHistorialPostulacion, entity.fechaHistorialPostulacion) &&
                Objects.equals(this.detalleHistorialPostulacion, entity.detalleHistorialPostulacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPostulacionId, idEstadoPostulacion, fechaHistorialPostulacion, detalleHistorialPostulacion);
    }
}