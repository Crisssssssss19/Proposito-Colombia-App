package com.procol.procolombia.entities.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.procol.procolombia.entities.EstadosPostulacione}
 */
public class EstadosPostulacioneDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(max = 150)
    private final String nombreEstadoPostulacion;
    @NotNull
    private final Short ordenEstadoPostulacion;
    private final Set<Integer> historialEstadosPostulacioneIds;

    public EstadosPostulacioneDto(Integer id, String nombreEstadoPostulacion, Short ordenEstadoPostulacion, Set<Integer> historialEstadosPostulacioneIds) {
        this.id = id;
        this.nombreEstadoPostulacion = nombreEstadoPostulacion;
        this.ordenEstadoPostulacion = ordenEstadoPostulacion;
        this.historialEstadosPostulacioneIds = historialEstadosPostulacioneIds;
    }

    public Integer getId() {
        return id;
    }

    public String getNombreEstadoPostulacion() {
        return nombreEstadoPostulacion;
    }

    public Short getOrdenEstadoPostulacion() {
        return ordenEstadoPostulacion;
    }

    public Set<Integer> getHistorialEstadosPostulacioneIds() {
        return historialEstadosPostulacioneIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadosPostulacioneDto entity = (EstadosPostulacioneDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nombreEstadoPostulacion, entity.nombreEstadoPostulacion) &&
                Objects.equals(this.ordenEstadoPostulacion, entity.ordenEstadoPostulacion) &&
                Objects.equals(this.historialEstadosPostulacioneIds, entity.historialEstadosPostulacioneIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreEstadoPostulacion, ordenEstadoPostulacion, historialEstadosPostulacioneIds);
    }
}