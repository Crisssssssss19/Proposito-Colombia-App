package com.procol.procolombia.vacante.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InteresId implements Serializable {

    private static final long serialVersionUID = 3592177676509978413L;

    @NotNull
    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;

    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
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
        InteresId entity = (InteresId) o;
        return Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.idEmpresa, entity.idEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idEmpresa);
    }

    public InteresId() {
    }

    public InteresId(Integer idEmpresa, Integer idUsuario) {
        this.idEmpresa = idEmpresa;
        this.idUsuario = idUsuario;
    }
}