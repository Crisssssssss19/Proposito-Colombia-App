package com.procol.procolombia.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "historial_estados_postulaciones")
public class HistorialEstadosPostulacione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historial_estados_postulaciones_id_gen")
    @SequenceGenerator(name = "historial_estados_postulaciones_id_gen", sequenceName = "historial_estados_postulaciones_id_historial_postulacion_seq", allocationSize = 1)
    @Column(name = "id_historial_postulacion", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_postulacion", nullable = false)
    private Postulacione idPostulacion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_estado_postulacion", nullable = false)
    private EstadosPostulacione idEstadoPostulacion;

    @NotNull
    @Column(name = "fecha_historial_postulacion", nullable = false)
    private Instant fechaHistorialPostulacion;

    @NotNull
    @Column(name = "detalle_historial_postulacion", nullable = false, length = Integer.MAX_VALUE)
    private String detalleHistorialPostulacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Postulacione getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Postulacione idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public EstadosPostulacione getIdEstadoPostulacion() {
        return idEstadoPostulacion;
    }

    public void setIdEstadoPostulacion(EstadosPostulacione idEstadoPostulacion) {
        this.idEstadoPostulacion = idEstadoPostulacion;
    }

    public Instant getFechaHistorialPostulacion() {
        return fechaHistorialPostulacion;
    }

    public void setFechaHistorialPostulacion(Instant fechaHistorialPostulacion) {
        this.fechaHistorialPostulacion = fechaHistorialPostulacion;
    }

    public String getDetalleHistorialPostulacion() {
        return detalleHistorialPostulacion;
    }

    public void setDetalleHistorialPostulacion(String detalleHistorialPostulacion) {
        this.detalleHistorialPostulacion = detalleHistorialPostulacion;
    }

}