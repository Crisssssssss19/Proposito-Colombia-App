package com.procol.procolombia.entities;

import com.procol.procolombia.entities.idCompuestas.RelVacantePalabraClaveId;
import jakarta.persistence.*;

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

