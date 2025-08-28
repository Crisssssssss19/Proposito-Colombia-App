package com.procol.procolombia.entities.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.HistorialEstadosVacante}
 */
public class HistorialEstadosVacanteDto implements Serializable {
    private final Integer id;
    private final Integer idVacante;
    private final Integer idEstadoVacante;
    @NotNull
    private final Instant fechaHistorialEstadoVacante;

    public HistorialEstadosVacanteDto(Integer id, Integer idVacanteId, Integer idEstadoVacanteId, Instant fechaHistorialEstadoVacante) {
        this.id = id;
        this.idVacante = idVacanteId;
        this.idEstadoVacante = idEstadoVacanteId;
        this.fechaHistorialEstadoVacante = fechaHistorialEstadoVacante;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public Integer getIdEstadoVacante() {
        return idEstadoVacante;
    }

    public Instant getFechaHistorialEstadoVacante() {
        return fechaHistorialEstadoVacante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistorialEstadosVacanteDto entity = (HistorialEstadosVacanteDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idVacante, entity.idVacante) &&
                Objects.equals(this.idEstadoVacante, entity.idEstadoVacante) &&
                Objects.equals(this.fechaHistorialEstadoVacante, entity.fechaHistorialEstadoVacante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idVacante, idEstadoVacante, fechaHistorialEstadoVacante);
    }
}