package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "contratos")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre; // Ej: Indefinido, Definido, Prestación de servicios

    @OneToMany(mappedBy = "contrato")
    private Set<Vacante> vacantes;


    public Contrato(Integer id, String nombre, Set<Vacante> vacantes) {
        this.id = id;
        this.nombre = nombre;
        this.vacantes = vacantes;
    }

    public Contrato() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(Set<Vacante> vacantes) {
        this.vacantes = vacantes;
    }
}
