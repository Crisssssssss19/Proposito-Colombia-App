package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.UsuarioRol}
 */
public class UsuarioRolDto implements Serializable {
    private final Integer idUsuario;
    private final Integer idRol;

    public UsuarioRolDto(Integer usuarioIdUsuario, Integer rolIdRol) {
        this.idUsuario = usuarioIdUsuario;
        this.idRol = rolIdRol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Integer getIdRol() {
        return idRol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioRolDto entity = (UsuarioRolDto) o;
        return Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.idRol, entity.idRol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idRol);
    }
}