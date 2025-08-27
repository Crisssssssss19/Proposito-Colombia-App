package com.procol.procolombia.entities.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Ingreso}
 */
public class IngresoDto implements Serializable {
    private final Integer id;
    private final Integer idUsuario;
    @NotNull
    private final Instant fechaIngreso;

    public IngresoDto(Integer id, Integer idUsuarioId, Instant fechaIngreso) {
        this.id = id;
        this.idUsuario = idUsuarioId;
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Instant getFechaIngreso() {
        return fechaIngreso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngresoDto entity = (IngresoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.fechaIngreso, entity.fechaIngreso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUsuario, fechaIngreso);
    }
}