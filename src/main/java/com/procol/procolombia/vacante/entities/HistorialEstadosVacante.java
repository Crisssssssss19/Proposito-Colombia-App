package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "historial_estados_vacantes")
public class HistorialEstadosVacante {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historial_estados_vacantes_id_gen")
    @SequenceGenerator(name = "historial_estados_vacantes_id_gen", sequenceName = "historial_estados_vacantes_id_historial_estado_vacante_seq", allocationSize = 1)
    @Column(name = "id_historial_estado_vacante", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_vacante", nullable = false)
    private Vacante idVacante;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_estado_vacante", nullable = false)
    private EstadosVacante idEstadoVacante;

    @NotNull
    @Column(name = "fecha_historial_estado_vacante", nullable = false)
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

    public EstadosVacante getIdEstadoVacante() {
        return idEstadoVacante;
    }

    public void setIdEstadoVacante(EstadosVacante idEstadoVacante) {
        this.idEstadoVacante = idEstadoVacante;
    }

    public Instant getFechaHistorialEstadoVacante() {
        return fechaHistorialEstadoVacante;
    }

    public void setFechaHistorialEstadoVacante(Instant fechaHistorialEstadoVacante) {
        this.fechaHistorialEstadoVacante = fechaHistorialEstadoVacante;
    }

}