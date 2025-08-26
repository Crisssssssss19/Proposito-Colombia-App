package com.procol.procolombia.entities;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "historial_estados_vacantes")
public class HistorialEstadoVacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial_estado_vacante")
    private Integer idHistorialEstadoVacante;

    @Column(name = "fecha_historial_estado_vacante", nullable = false)
    private Date fechaHistorialEstadoVacante;

    @ManyToOne
    @JoinColumn(name = "id_vacante", nullable = false)
    private Vacante vacante;

    @ManyToOne
    @JoinColumn(name = "id_estado_vacante", nullable = false)
    private EstadoVacante estadoVacante;

    public HistorialEstadoVacante(){}

    public Integer getIdHistorialEstadoVacante() {
        return idHistorialEstadoVacante;
    }

    public void setIdHistorialEstadoVacante(Integer idHistorialEstadoVacante) {
        this.idHistorialEstadoVacante = idHistorialEstadoVacante;
    }

    public Date getFechaHistorialEstadoVacante() {
        return fechaHistorialEstadoVacante;
    }

    public void setFechaHistorialEstadoVacante(Date fechaHistorialEstadoVacante) {
        this.fechaHistorialEstadoVacante = fechaHistorialEstadoVacante;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public EstadoVacante getEstadoVacante() {
        return estadoVacante;
    }

    public void setEstadoVacante(EstadoVacante estadoVacante) {
        this.estadoVacante = estadoVacante;
    }
}