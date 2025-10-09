package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Entity
@Table(name= "historial_intereses")
public class HistorialInteres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistorialInteres;

    @NotNull
    @Column(name = "tipo_interes", nullable = false)
    private Short tipoInteres;

    @Column(name = "fecha_interes", nullable = false, updatable = false)
    private Instant fechaInteres;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false),
            @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false)
    })
    private Interes interes;

    public HistorialInteres(Integer idHistorialInteres, Short tipoInteres, Interes interes) {
        this.idHistorialInteres = idHistorialInteres;
        this.tipoInteres = tipoInteres;
        this.fechaInteres = Instant.now();
        this.interes = interes;
    }

    public HistorialInteres() {

    }

    public Integer getIdHistorialInteres() {
        return idHistorialInteres;
    }

    public void setIdHistorialInteres(Integer idHistorialInteres) {
        this.idHistorialInteres = idHistorialInteres;
    }

    public @NotNull Short getTipoInteres() {
        return tipoInteres;
    }

    public void setTipoInteres(@NotNull Short tipoInteres) {
        this.tipoInteres = tipoInteres;
    }

    public Instant getFechaInteres() {
        return fechaInteres;
    }

    public void setFechaInteres(Instant fechaInteres) {
        this.fechaInteres = fechaInteres;
    }

    public Interes getInteres() {
        return interes;
    }

    public void setInteres(Interes interes) {
        this.interes = interes;
    }
}
