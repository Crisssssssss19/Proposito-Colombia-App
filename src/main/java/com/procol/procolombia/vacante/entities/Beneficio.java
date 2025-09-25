package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "beneficios")
public class Beneficio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_beneficio", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "nombre_beneficio", nullable = false)
    private String nombreBeneficio;

    @NotNull
    @Column(name = "descripcion_beneficio", nullable = false, length = 300)
    private String descripcionBeneficio;

    @NotNull
    @Column(name = "orden_beneficio", nullable = false)
    private Short ordenBeneficio;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_vacante", nullable = false)
    private Vacante idvacante;

    public Beneficio() {
    }

    public Beneficio(Integer id, Vacante idvacante, Short ordenBeneficio, String descripcionBeneficio, String nombreBeneficio) {
        this.id = id;
        this.idvacante = idvacante;
        this.ordenBeneficio = ordenBeneficio;
        this.descripcionBeneficio = descripcionBeneficio;
        this.nombreBeneficio = nombreBeneficio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreBeneficio() {
        return nombreBeneficio;
    }

    public void setNombreBeneficio(String nombreBeneficio) {
        this.nombreBeneficio = nombreBeneficio;
    }

    public String getDescripcionBeneficio() {
        return descripcionBeneficio;
    }

    public void setDescripcionBeneficio(String descripcionBeneficio) {
        this.descripcionBeneficio = descripcionBeneficio;
    }

    public Short getOrdenBeneficio() {
        return ordenBeneficio;
    }

    public void setOrdenBeneficio(Short ordenBeneficio) {
        this.ordenBeneficio = ordenBeneficio;
    }

    public Vacante getIdvacante() {
        return idvacante;
    }

    public void setIdvacante(Vacante idvacante) {
        this.idvacante = idvacante;
    }
}
