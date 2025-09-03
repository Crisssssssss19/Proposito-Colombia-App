package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "estados_vacantes")
public class EstadosVacante {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estados_vacantes_id_gen")
    @SequenceGenerator(name = "estados_vacantes_id_gen", sequenceName = "estados_vacantes_id_estado_vacante_seq", allocationSize = 1)
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
    private Set<HistorialEstadosVacante> historialEstadosVacantes = new LinkedHashSet<>();

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

    public Set<HistorialEstadosVacante> getHistorialEstadosVacantes() {
        return historialEstadosVacantes;
    }

    public void setHistorialEstadosVacantes(Set<HistorialEstadosVacante> historialEstadosVacantes) {
        this.historialEstadosVacantes = historialEstadosVacantes;
    }

}