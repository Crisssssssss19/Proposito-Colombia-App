package com.procol.procolombia.auth.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Entity
@Table(name = "ingresos")
public class Ingreso {
    @Id
    @Column(name = "id_ingreso", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Acceso idUsuario;

    @NotNull
    @Column(name = "fecha_ingreso", nullable = false)
    private Instant fechaIngreso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Acceso getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Acceso idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Instant getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Instant fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

}