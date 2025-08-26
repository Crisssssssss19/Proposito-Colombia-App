package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Ingreso}
 */
public class IngresoDto implements Serializable {
    private final Integer idIngreso;
    private final Date fechaIngreso;
    private final Integer accesoIdUsuario;

    public IngresoDto(Integer idIngreso, Date fechaIngreso, Integer accesoIdUsuario) {
        this.idIngreso = idIngreso;
        this.fechaIngreso = fechaIngreso;
        this.accesoIdUsuario = accesoIdUsuario;
    }

    public Integer getIdIngreso() {
        return idIngreso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public Integer getAccesoIdUsuario() {
        return accesoIdUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngresoDto entity = (IngresoDto) o;
        return Objects.equals(this.idIngreso, entity.idIngreso) &&
                Objects.equals(this.fechaIngreso, entity.fechaIngreso) &&
                Objects.equals(this.accesoIdUsuario, entity.accesoIdUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIngreso, fechaIngreso, accesoIdUsuario);
    }
}