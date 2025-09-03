package com.procol.procolombia.auth.dto;

import com.procol.procolombia.auth.entities.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Role}
 */
public class RoleDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(max = 150)
    private final String nombreRol;
    @NotNull
    private final Short estadoRol;
    private final Set<Integer> usuarioIds;

    public RoleDto(Integer id, String nombreRol, Short estadoRol, Set<Integer> usuarioIds) {
        this.id = id;
        this.nombreRol = nombreRol;
        this.estadoRol = estadoRol;
        this.usuarioIds = usuarioIds;
    }

    public Integer getId() {
        return id;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public Short getEstadoRol() {
        return estadoRol;
    }

    public Set<Integer> getUsuarioIds() {
        return usuarioIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto entity = (RoleDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nombreRol, entity.nombreRol) &&
                Objects.equals(this.estadoRol, entity.estadoRol) &&
                Objects.equals(this.usuarioIds, entity.usuarioIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreRol, estadoRol, usuarioIds);
    }
}