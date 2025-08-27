package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.lang.Integer;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Rol}
 */
public class RolDto implements Serializable {
    private final Integer idRol;
    private final String nombreRol;
    private final Short estadoRol;
    private final List<Integer> idUsuarios;

    public RolDto(Integer idRol, String nombreRol, Short estadoRol, List<Integer> usuarioIdUsuarios) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.estadoRol = estadoRol;
        this.idUsuarios = usuarioIdUsuarios;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public Short getEstadoRol() {
        return estadoRol;
    }

    public List<Integer> getIdUsuarios() {
        return idUsuarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolDto entity = (RolDto) o;
        return Objects.equals(this.idRol, entity.idRol) &&
                Objects.equals(this.nombreRol, entity.nombreRol) &&
                Objects.equals(this.estadoRol, entity.estadoRol) &&
                Objects.equals(this.idUsuarios, entity.idUsuarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, nombreRol, estadoRol, idUsuarios);
    }
}