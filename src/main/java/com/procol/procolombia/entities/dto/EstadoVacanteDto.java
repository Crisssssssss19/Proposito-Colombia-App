package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.EstadoVacante}
 */
public class EstadoVacanteDto implements Serializable {
    private final Integer idEstadoVacante;
    private final String nombreEstadoVacante;
    private final Short ordenEstadoVacante;
    private final List<Integer> IdHistorialEstadoVacantes;

    public EstadoVacanteDto(Integer idEstadoVacante, String nombreEstadoVacante, Short ordenEstadoVacante, List<Integer> historialIdHistorialEstadoVacantes) {
        this.idEstadoVacante = idEstadoVacante;
        this.nombreEstadoVacante = nombreEstadoVacante;
        this.ordenEstadoVacante = ordenEstadoVacante;
        this.IdHistorialEstadoVacantes = historialIdHistorialEstadoVacantes;
    }

    public Integer getIdEstadoVacante() {
        return idEstadoVacante;
    }

    public String getNombreEstadoVacante() {
        return nombreEstadoVacante;
    }

    public Short getOrdenEstadoVacante() {
        return ordenEstadoVacante;
    }

    public List<Integer> getIdHistorialEstadoVacantes() {
        return IdHistorialEstadoVacantes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoVacanteDto entity = (EstadoVacanteDto) o;
        return Objects.equals(this.idEstadoVacante, entity.idEstadoVacante) &&
                Objects.equals(this.nombreEstadoVacante, entity.nombreEstadoVacante) &&
                Objects.equals(this.ordenEstadoVacante, entity.ordenEstadoVacante) &&
                Objects.equals(this.IdHistorialEstadoVacantes, entity.IdHistorialEstadoVacantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstadoVacante, nombreEstadoVacante, ordenEstadoVacante, IdHistorialEstadoVacantes);
    }
}