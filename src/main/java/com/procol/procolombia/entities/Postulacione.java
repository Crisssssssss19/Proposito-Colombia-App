package com.procol.procolombia.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "postulaciones")
public class Postulacione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postulaciones_id_gen")
    @SequenceGenerator(name = "postulaciones_id_gen", sequenceName = "postulaciones_id_postulacion_seq", allocationSize = 1)
    @Column(name = "id_postulacion", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_vacante", nullable = false)
    private Vacante idVacante;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @NotNull
    @Column(name = "fecha_postulacion", nullable = false)
    private Instant fechaPostulacion;

    @NotNull
    @ColumnDefault("4")
    @Column(name = "correspondencia_postulacion", nullable = false)
    private Short correspondenciaPostulacion;

    @NotNull
    @Column(name = "estado_postulacion", nullable = false)
    private Short estadoPostulacion;

    @OneToMany(mappedBy = "idPostulacion")
    private Set<HistorialEstadosPostulacione> historialEstadosPostulaciones = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPostulacion")
    private Set<Mensaje> mensajes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vacante getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Vacante idVacante) {
        this.idVacante = idVacante;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Instant getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(Instant fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }

    public Short getCorrespondenciaPostulacion() {
        return correspondenciaPostulacion;
    }

    public void setCorrespondenciaPostulacion(Short correspondenciaPostulacion) {
        this.correspondenciaPostulacion = correspondenciaPostulacion;
    }

    public Short getEstadoPostulacion() {
        return estadoPostulacion;
    }

    public void setEstadoPostulacion(Short estadoPostulacion) {
        this.estadoPostulacion = estadoPostulacion;
    }

    public Set<HistorialEstadosPostulacione> getHistorialEstadosPostulaciones() {
        return historialEstadosPostulaciones;
    }

    public void setHistorialEstadosPostulaciones(Set<HistorialEstadosPostulacione> historialEstadosPostulaciones) {
        this.historialEstadosPostulaciones = historialEstadosPostulaciones;
    }

    public Set<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(Set<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

}