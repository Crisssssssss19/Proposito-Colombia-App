package com.procol.procolombia.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "estados_postulaciones")
public class EstadosPostulacione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estados_postulaciones_id_gen")
    @SequenceGenerator(name = "estados_postulaciones_id_gen", sequenceName = "estados_postulaciones_id_estado_postulacion_seq", allocationSize = 1)
    @Column(name = "id_estado_postulacion", nullable = false)
    private Integer id;

    @Size(max = 150)
    @NotNull
    @Column(name = "nombre_estado_postulacion", nullable = false, length = 150)
    private String nombreEstadoPostulacion;

    @NotNull
    @Column(name = "orden_estado_postulacion", nullable = false)
    private Short ordenEstadoPostulacion;

    @OneToMany(mappedBy = "idEstadoPostulacion")
    private Set<HistorialEstadosPostulacione> historialEstadosPostulaciones = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<HistorialEstadosPostulacione> getHistorialEstadosPostulaciones() {
        return historialEstadosPostulaciones;
    }

    public void setHistorialEstadosPostulaciones(Set<HistorialEstadosPostulacione> historialEstadosPostulaciones) {
        this.historialEstadosPostulaciones = historialEstadosPostulaciones;
    }

}