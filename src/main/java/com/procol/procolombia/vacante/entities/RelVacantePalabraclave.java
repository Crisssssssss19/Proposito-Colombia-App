package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "rel_vacante_palabraclave")
public class RelVacantePalabraclave {
    @SequenceGenerator(name = "rel_vacante_palabraclave_id_gen", sequenceName = "requisitos_id_requisito_seq", allocationSize = 1)
    @EmbeddedId
    private RelVacantePalabraclaveId id;

    @MapsId("idVacante")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_vacante", nullable = false)
    private Vacante idVacante;

    @MapsId("idPalabraClave")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_palabra_clave", nullable = false)
    private PalabrasClave idPalabraClave;

    public RelVacantePalabraclaveId getId() {
        return id;
    }

    public void setId(RelVacantePalabraclaveId id) {
        this.id = id;
    }

    public Vacante getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Vacante idVacante) {
        this.idVacante = idVacante;
    }

    public PalabrasClave getIdPalabraClave() {
        return idPalabraClave;
    }

    public void setIdPalabraClave(PalabrasClave idPalabraClave) {
        this.idPalabraClave = idPalabraClave;
    }

}