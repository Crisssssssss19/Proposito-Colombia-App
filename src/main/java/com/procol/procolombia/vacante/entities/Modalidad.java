package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "modalidades")
public class Modalidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modalidad", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre; // Ej: Presencial, Remoto, Híbrido

    @OneToMany(mappedBy = "modalidad")
    private Set<Vacante> vacantes;

    public Modalidad() {
    }

    public Modalidad(Integer id, String nombre, Set<Vacante> vacantes) {
        this.id = id;
        this.nombre = nombre;
        this.vacantes = vacantes;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
