package com.procol.procolombia.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "requisitos")
public class Requisito {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requisitos_id_gen")
    @SequenceGenerator(name = "requisitos_id_gen", sequenceName = "requisitos_id_requisito_seq", allocationSize = 1)
    @Column(name = "id_requisito", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_vacante", nullable = false)
    private Vacante idVacante;

    @Size(max = 200)
    @NotNull
    @Column(name = "titulo_requisito", nullable = false, length = 200)
    private String tituloRequisito;

    @NotNull
    @Column(name = "detalle_requisito", nullable = false, length = Integer.MAX_VALUE)
    private String detalleRequisito;

    @NotNull
    @Column(name = "orden_requisito", nullable = false)
    private Short ordenRequisito;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vacante getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Vacante idVacante) {
        this.idVacante = idVacante;
    }

    public String getTituloRequisito() {
        return tituloRequisito;
    }

    public void setTituloRequisito(String tituloRequisito) {
        this.tituloRequisito = tituloRequisito;
    }

    public String getDetalleRequisito() {
        return detalleRequisito;
    }

    public void setDetalleRequisito(String detalleRequisito) {
        this.detalleRequisito = detalleRequisito;
    }

    public Short getOrdenRequisito() {
        return ordenRequisito;
    }

    public void setOrdenRequisito(Short ordenRequisito) {
        this.ordenRequisito = ordenRequisito;
    }

}