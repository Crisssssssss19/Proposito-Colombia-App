package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "Periodicidades_pago")
public class PeriodicidadPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_periodicidad_pago", nullable = false)
    private Integer id;

    @Size(max = 150)
    @NotNull
    @Column (name = "nombre", nullable = false, length = 150)
    private String nombre;

    @OneToMany(mappedBy = "periodicidadPago")
    private Set<Vacante> vacantes;

    public PeriodicidadPago(Integer id, String nombre, Set<Vacante> vacantes) {
        this.id = id;
        this.nombre = nombre;
        this.vacantes = vacantes;
    }
    public PeriodicidadPago() {

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
