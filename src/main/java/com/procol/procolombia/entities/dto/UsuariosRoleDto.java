package com.procol.procolombia.entities.dto;

import com.procol.procolombia.entities.UsuariosRoleId;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.UsuariosRole}
 */
public class UsuariosRoleDto implements Serializable {
    private final UsuariosRoleId id;
    private final Integer idRol;
    private final Integer idUsuario;

    public UsuariosRoleDto(UsuariosRoleId id, Integer idRolId, Integer idUsuarioId) {
        this.id = id;
        this.idRol = idRolId;
        this.idUsuario = idUsuarioId;
    }

    public UsuariosRoleId getId() {
        return id;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuariosRoleDto entity = (UsuariosRoleDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idRol, entity.idRol) &&
                Objects.equals(this.idUsuario, entity.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idRol, idUsuario);
    }
}