package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "jornadas")
public class Jornada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jornada", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre; // Ej: Tiempo completo, Medio tiempo, etc.

    @OneToMany(mappedBy = "jornada")
    private Set<Vacante> vacantes;


    public Jornada() {
    }

    public Jornada(Integer id, String nombre, Set<Vacante> vacantes) {
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
