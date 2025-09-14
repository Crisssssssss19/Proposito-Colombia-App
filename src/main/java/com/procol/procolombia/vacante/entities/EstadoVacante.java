package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "estados_vacantes")
public class EstadoVacante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_vacante", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre_estado_vacante", nullable = false, length = 100)
    private String nombreEstadoVacante;

    @NotNull
    @Column(name = "orden_estado_vacante", nullable = false)
    private Short ordenEstadoVacante;

    @OneToMany(mappedBy = "idEstadoVacante")
    private Set<HistorialEstadoVacante> historialEstadoVacantes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<HistorialEstadoVacante> getHistorialEstadosVacantes() {
        return historialEstadoVacantes;
    }

    public void setHistorialEstadosVacantes(Set<HistorialEstadoVacante> historialEstadoVacantes) {
        this.historialEstadoVacantes = historialEstadoVacantes;
    }
    public EstadoVacante() {
    }
    public EstadoVacante(Integer id,
                         String nombreEstadoVacante,
                         Short ordenEstadoVacante,
                         Set<HistorialEstadoVacante> historialEstadoVacantes) {
        this.id = id;
        this.nombreEstadoVacante = nombreEstadoVacante;
        this.ordenEstadoVacante = ordenEstadoVacante;
        this.historialEstadoVacantes = historialEstadoVacantes;
    }

}