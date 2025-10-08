package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "rangos_salariales")
public class RangoSalarial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rango", nullable = false)
    private Integer id;

    @Column(name = "salario_min", nullable = false)
    private Double salarioMin;

    @Column(name = "salario_max", nullable = false)
    private Double salarioMax;

    @OneToMany(mappedBy = "rangoSalarial")
    private Set<Vacante> vacantes;

    public RangoSalarial() {
    }

    public RangoSalarial(Integer id, Set<Vacante> vacantes, Double salarioMax, Double salarioMin) {
        this.id = id;
        this.vacantes = vacantes;
        this.salarioMax = salarioMax;
        this.salarioMin = salarioMin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(Set<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

    public Double getSalarioMin() {
        return salarioMin;
    }

    public void setSalarioMin(Double salarioMin) {
        this.salarioMin = salarioMin;
    }

    public Double getSalarioMax() {
        return salarioMax;
    }

    public void setSalarioMax(Double salarioMax) {
        this.salarioMax = salarioMax;
    }
}
