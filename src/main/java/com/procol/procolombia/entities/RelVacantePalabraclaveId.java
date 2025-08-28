package com.procol.procolombia.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RelVacantePalabraclaveId implements Serializable {
    private static final long serialVersionUID = 6518120497850241657L;
    @NotNull
    @Column(name = "id_vacante", nullable = false)
    private Integer idVacante;

    @NotNull
    @Column(name = "id_palabra_clave", nullable = false)
    private Integer idPalabraClave;

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
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
        RelVacantePalabraclaveId entity = (RelVacantePalabraclaveId) o;
        return Objects.equals(this.idPalabraClave, entity.idPalabraClave) &&
                Objects.equals(this.idVacante, entity.idVacante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPalabraClave, idVacante);
    }

}