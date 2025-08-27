package com.procol.procolombia.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RelUsuarioPalabraclaveId implements Serializable {
    private static final long serialVersionUID = 9216297448441729119L;
    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @NotNull
    @Column(name = "id_palabra_clave", nullable = false)
    private Integer idPalabraClave;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPalabraClave() {
        return idPalabraClave;
    }

    public void setIdPalabraClave(Integer idPalabraClave) {
        this.idPalabraClave = idPalabraClave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RelUsuarioPalabraclaveId entity = (RelUsuarioPalabraclaveId) o;
        return Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.idPalabraClave, entity.idPalabraClave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idPalabraClave);
    }

}