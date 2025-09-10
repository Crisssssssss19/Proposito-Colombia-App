package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "requisitos")
public class Requisito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRequisito", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "idVacante", nullable = false)
    private Vacante idVacante;

    @Size(max = 200)
    @NotNull
    @Column(name = "tituloRequisito", nullable = false, length = 200)
    private String tituloRequisito;

    @NotNull
    @Column(name = "detalleRequisito", nullable = false, length = Integer.MAX_VALUE)
    private String detalleRequisito;

    @NotNull
    @Column(name = "ordenRequisito", nullable = false)
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

    public Requisito() {
    }

    public Requisito(Integer id, Vacante idVacante, String tituloRequisito, String detalleRequisito, Short ordenRequisito) {
        this.id = id;
        this.idVacante = idVacante;
        this.tituloRequisito = tituloRequisito;
        this.detalleRequisito = detalleRequisito;
        this.ordenRequisito = ordenRequisito;
    }
}