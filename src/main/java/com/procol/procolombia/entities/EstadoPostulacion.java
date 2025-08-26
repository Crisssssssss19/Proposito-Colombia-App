package com.procol.procolombia.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "estados_postulaciones")
public class EstadoPostulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_postulacion")
    private Integer idEstadoPostulacion;

    @Column(name = "nombre_estado_postulacion", nullable = false, length = 150)
    private String nombreEstadoPostulacion;

    @Column(name = "orden_estado_postulacion", nullable = false)
    private Short ordenEstadoPostulacion;

    @OneToMany(mappedBy = "estadoPostulacion")
    private List<HistorialEstadoPostulacion> historial;

    public Integer getIdEstadoPostulacion() {
        return idEstadoPostulacion;
    }

    public EstadoPostulacion(){}

    public void setIdEstadoPostulacion(Integer idEstadoPostulacion) {
        this.idEstadoPostulacion = idEstadoPostulacion;
    }

    public String getNombreEstadoPostulacion() {
        return nombreEstadoPostulacion;
    }

    public void setNombreEstadoPostulacion(String nombreEstadoPostulacion) {
        this.nombreEstadoPostulacion = nombreEstadoPostulacion;
    }

    public Short getOrdenEstadoPostulacion() {
        return ordenEstadoPostulacion;
    }

    public void setOrdenEstadoPostulacion(Short ordenEstadoPostulacion) {
        this.ordenEstadoPostulacion = ordenEstadoPostulacion;
    }

    public List<HistorialEstadoPostulacion> getHistorial() {
        return historial;
    }

    public void setHistorial(List<HistorialEstadoPostulacion> historial) {
        this.historial = historial;
    }
}
