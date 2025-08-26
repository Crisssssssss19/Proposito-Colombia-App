package com.procol.procolombia.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "historial_estados_postulaciones")
public class HistorialEstadoPostulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial_postulacion")
    private Integer idHistorialPostulacion;

    @Column(name = "fecha_historial_postulacion", nullable = false)
    private Date fechaHistorialPostulacion;

    @Column(name = "detalle_historial_postulacion", nullable = false, columnDefinition = "TEXT")
    private String detalleHistorialPostulacion;

    @ManyToOne
    @JoinColumn(name = "id_postulacion", nullable = false)
    private Postulacion postulacion;

    @ManyToOne
    @JoinColumn(name = "id_estado_postulacion", nullable = false)
    private EstadoPostulacion estadoPostulacion;

    public HistorialEstadoPostulacion(){}

    public Integer getIdHistorialPostulacion() {
        return idHistorialPostulacion;
    }

    public void setIdHistorialPostulacion(Integer idHistorialPostulacion) {
        this.idHistorialPostulacion = idHistorialPostulacion;
    }

    public Date getFechaHistorialPostulacion() {
        return fechaHistorialPostulacion;
    }

    public void setFechaHistorialPostulacion(Date fechaHistorialPostulacion) {
        this.fechaHistorialPostulacion = fechaHistorialPostulacion;
    }

    public String getDetalleHistorialPostulacion() {
        return detalleHistorialPostulacion;
    }

    public void setDetalleHistorialPostulacion(String detalleHistorialPostulacion) {
        this.detalleHistorialPostulacion = detalleHistorialPostulacion;
    }

    public Postulacion getPostulacion() {
        return postulacion;
    }

    public void setPostulacion(Postulacion postulacion) {
        this.postulacion = postulacion;
    }

    public EstadoPostulacion getEstadoPostulacion() {
        return estadoPostulacion;
    }

    public void setEstadoPostulacion(EstadoPostulacion estadoPostulacion) {
        this.estadoPostulacion = estadoPostulacion;
    }
}