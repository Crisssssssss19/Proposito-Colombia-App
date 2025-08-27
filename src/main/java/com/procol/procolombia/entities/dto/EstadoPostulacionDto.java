package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.EstadoPostulacion}
 */
public class EstadoPostulacionDto implements Serializable {
    private final Integer idEstadoPostulacion;
    private final String nombreEstadoPostulacion;
    private final Short ordenEstadoPostulacion;
    private final List<Integer> idHistorialPostulaciones;

    public EstadoPostulacionDto(Integer idEstadoPostulacion, String nombreEstadoPostulacion, Short ordenEstadoPostulacion, List<Integer> historialIdHistorialPostulacions) {
        this.idEstadoPostulacion = idEstadoPostulacion;
        this.nombreEstadoPostulacion = nombreEstadoPostulacion;
        this.ordenEstadoPostulacion = ordenEstadoPostulacion;
        this.idHistorialPostulaciones = historialIdHistorialPostulacions;
    }

    public Integer getIdEstadoPostulacion() {
        return idEstadoPostulacion;
    }

    public String getNombreEstadoPostulacion() {
        return nombreEstadoPostulacion;
    }

    public Short getOrdenEstadoPostulacion() {
        return ordenEstadoPostulacion;
    }

    public List<Integer> getIdHistorialPostulaciones() {
        return idHistorialPostulaciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoPostulacionDto entity = (EstadoPostulacionDto) o;
        return Objects.equals(this.idEstadoPostulacion, entity.idEstadoPostulacion) &&
                Objects.equals(this.nombreEstadoPostulacion, entity.nombreEstadoPostulacion) &&
                Objects.equals(this.ordenEstadoPostulacion, entity.ordenEstadoPostulacion) &&
                Objects.equals(this.idHistorialPostulaciones, entity.idHistorialPostulaciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstadoPostulacion, nombreEstadoPostulacion, ordenEstadoPostulacion, idHistorialPostulaciones);
    }
}