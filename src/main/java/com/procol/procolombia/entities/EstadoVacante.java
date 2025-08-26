package com.procol.procolombia.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "estados_vacantes")
public class EstadoVacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_vacante")
    private Integer idEstadoVacante;

    @Column(name = "nombre_estado_vacante", nullable = false, length = 100)
    private String nombreEstadoVacante;

    @Column(name = "orden_estado_vacante", nullable = false)
    private Short ordenEstadoVacante;

    @OneToMany(mappedBy = "estadoVacante")
    private List<HistorialEstadoVacante> historial;

    public EstadoVacante(){}

    public Integer getIdEstadoVacante() {
        return idEstadoVacante;
    }

    public void setIdEstadoVacante(Integer idEstadoVacante) {
        this.idEstadoVacante = idEstadoVacante;
    }

    public String getNombreEstadoVacante() {
        return nombreEstadoVacante;
    }

    public void setNombreEstadoVacante(String nombreEstadoVacante) {
        this.nombreEstadoVacante = nombreEstadoVacante;
    }

    public Short getOrdenEstadoVacante() {
        return ordenEstadoVacante;
    }

    public void setOrdenEstadoVacante(Short ordenEstadoVacante) {
        this.ordenEstadoVacante = ordenEstadoVacante;
    }

    public List<HistorialEstadoVacante> getHistorial() {
        return historial;
    }

    public void setHistorial(List<HistorialEstadoVacante> historial) {
        this.historial = historial;
    }
}