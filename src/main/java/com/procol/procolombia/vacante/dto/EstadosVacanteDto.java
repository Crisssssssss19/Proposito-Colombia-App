package com.procol.procolombia.vacante.dto;

import com.procol.procolombia.vacante.entities.EstadosVacante;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link EstadosVacante}
 */
public class EstadosVacanteDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(max = 100)
    private final String nombreEstadoVacante;
    @NotNull
    private final Short ordenEstadoVacante;
    private final Set<Integer> historialEstadosVacanteIds;

    public EstadosVacanteDto(Integer id, String nombreEstadoVacante, Short ordenEstadoVacante, Set<Integer> historialEstadosVacanteIds) {
        this.id = id;
        this.nombreEstadoVacante = nombreEstadoVacante;
        this.ordenEstadoVacante = ordenEstadoVacante;
        this.historialEstadosVacanteIds = historialEstadosVacanteIds;
    }

    public Integer getId() {
        return id;
    }

    public String getNombreEstadoVacante() {
        return nombreEstadoVacante;
    }

    public Short getOrdenEstadoVacante() {
        return ordenEstadoVacante;
    }

    public Set<Integer> getHistorialEstadosVacanteIds() {
        return historialEstadosVacanteIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadosVacanteDto entity = (EstadosVacanteDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nombreEstadoVacante, entity.nombreEstadoVacante) &&
                Objects.equals(this.ordenEstadoVacante, entity.ordenEstadoVacante) &&
                Objects.equals(this.historialEstadosVacanteIds, entity.historialEstadosVacanteIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreEstadoVacante, ordenEstadoVacante, historialEstadosVacanteIds);
    }
}