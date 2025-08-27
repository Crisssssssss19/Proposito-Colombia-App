package com.procol.procolombia.entities.idCompuestas;

import java.util.Objects;

public class UsuarioRolId implements java.io.Serializable {
    private Integer usuario;
    private Integer rol;

    public UsuarioRolId() {
    }

    public UsuarioRolId(Integer usuario, Integer rol) {
        this.usuario = usuario;
        this.rol = rol;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioRolId that = (UsuarioRolId) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(rol, that.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, rol);
    }
}