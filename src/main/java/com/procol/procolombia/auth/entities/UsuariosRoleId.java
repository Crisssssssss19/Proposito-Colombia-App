package com.procol.procolombia.auth.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuariosRoleId implements Serializable {
    private static final long serialVersionUID = 7231276586418985689L;
    @NotNull
    @Column(name = "id_rol", nullable = false)
    private Integer idRol;

    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    public UsuariosRoleId(Integer idRol, Integer idUsuario) {
        this.idRol = idRol;
        this.idUsuario = idUsuario;
    }

    public UsuariosRoleId() {

    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsuariosRoleId entity = (UsuariosRoleId) o;
        return Objects.equals(this.idRol, entity.idRol) &&
                Objects.equals(this.idUsuario, entity.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, idUsuario);
    }

}