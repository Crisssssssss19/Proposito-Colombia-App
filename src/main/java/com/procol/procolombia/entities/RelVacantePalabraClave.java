package com.procol.procolombia.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "rel_vacante_palabraclave")
@IdClass(RelVacantePalabraClaveId.class)
public class RelVacantePalabraClave {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_vacante", nullable = false)
    private Vacante vacante;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_palabra_clave", nullable = false)
    private PalabraClave palabraClave;

    public RelVacantePalabraClave(){}

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public PalabraClave getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(PalabraClave palabraClave) {
        this.palabraClave = palabraClave;
    }
}

class RelVacantePalabraClaveId implements java.io.Serializable {
    private Integer vacante;
    private Integer palabraClave;

    public RelVacantePalabraClaveId() {
    }

    public RelVacantePalabraClaveId(Integer vacante, Integer palabraClave) {
        this.vacante = vacante;
        this.palabraClave = palabraClave;
    }

    public Integer getVacante() {
        return vacante;
    }

    public void setVacante(Integer vacante) {
        this.vacante = vacante;
    }

    public Integer getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(Integer palabraClave) {
        this.palabraClave = palabraClave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelVacantePalabraClaveId that = (RelVacantePalabraClaveId) o;
        return Objects.equals(vacante, that.vacante) && Objects.equals(palabraClave, that.palabraClave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacante, palabraClave);
    }
}