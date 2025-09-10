package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "historial_estados_vacantes")
public class HistorialEstadoVacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHistorialEstadoVacante", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "idvacante", nullable = false)
    private Vacante idVacante;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "idEstadoVacante", nullable = false)
    private EstadoVacante idEstadoVacante;

    @NotNull
    @Column(name = "fechaHistorialEstadoVacante", nullable = false)
    private Instant fechaHistorialEstadoVacante;

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

    public EstadoVacante getIdEstadoVacante() {
        return idEstadoVacante;
    }

    public void setIdEstadoVacante(EstadoVacante idEstadoVacante) {
        this.idEstadoVacante = idEstadoVacante;
    }

    public Instant getFechaHistorialEstadoVacante() {
        return fechaHistorialEstadoVacante;
    }

    public void setFechaHistorialEstadoVacante(Instant fechaHistorialEstadoVacante) {
        this.fechaHistorialEstadoVacante = fechaHistorialEstadoVacante;
    }
    public HistorialEstadoVacante() {
    }

    public HistorialEstadoVacante(Integer id, Vacante idVacante, EstadoVacante idEstadoVacante, Instant fechaHistorialEstadoVacante) {
        this.id = id;
        this.idVacante = idVacante;
        this.idEstadoVacante = idEstadoVacante;
        this.fechaHistorialEstadoVacante = fechaHistorialEstadoVacante;
    }

}