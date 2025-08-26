package com.procol.procolombia.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "requisitos")
public class Requisito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requisito")
    private Integer idRequisito;

    @Column(name = "titulo_requisito", nullable = false, length = 200)
    private String tituloRequisito;

    @Column(name = "detalle_requisito", nullable = false, columnDefinition = "TEXT")
    private String detalleRequisito;

    @Column(name = "orden_requisito", nullable = false)
    private Short ordenRequisito;

    @ManyToOne
    @JoinColumn(name = "id_vacante", nullable = false)
    private Vacante vacante;

    public Requisito(){}

    public Integer getIdRequisito() {
        return idRequisito;
    }

    public void setIdRequisito(Integer idRequisito) {
        this.idRequisito = idRequisito;
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

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }
}