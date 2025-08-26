package com.procol.procolombia.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "postulaciones")
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_postulacion")
    private Integer idPostulacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_postulacion", nullable = false)
    private Date fechaPostulacion;

    @Column(name = "correspondencia_postulacion", nullable = false)
    private Short correspondenciaPostulacion;

    @Column(name = "estado_postulacion", nullable = false)
    private Short estadoPostulacion;

    @ManyToOne
    @JoinColumn(name = "id_vacante", nullable = false)
    private Vacante vacante;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "postulacion")
    private List<Mensaje> mensajes;

    @OneToMany(mappedBy = "postulacion")
    private List<HistorialEstadoPostulacion> historial;

    public Postulacion(){}

    public Integer getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Integer idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public Date getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(Date fechaPostulacion) {
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

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public List<HistorialEstadoPostulacion> getHistorial() {
        return historial;
    }

    public void setHistorial(List<HistorialEstadoPostulacion> historial) {
        this.historial = historial;
    }
}
