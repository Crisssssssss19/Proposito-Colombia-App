package com.procol.procolombia.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Integer idMensaje;

    @Column(name = "texto_mensaje", nullable = false, columnDefinition = "TEXT")
    private String textoMensaje;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_mensaje", nullable = false)
    private Date fechaMensaje;

    @Column(name = "estado_mensaje", nullable = false)
    private Short estadoMensaje = 1;

    @ManyToOne
    @JoinColumn(name = "id_postulacion", nullable = false)
    private Postulacion postulacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario_responde", nullable = false)
    private Usuario usuarioResponde;

    public Mensaje(){}

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getTextoMensaje() {
        return textoMensaje;
    }

    public void setTextoMensaje(String textoMensaje) {
        this.textoMensaje = textoMensaje;
    }

    public Date getFechaMensaje() {
        return fechaMensaje;
    }

    public void setFechaMensaje(Date fechaMensaje) {
        this.fechaMensaje = fechaMensaje;
    }

    public Short getEstadoMensaje() {
        return estadoMensaje;
    }

    public void setEstadoMensaje(Short estadoMensaje) {
        this.estadoMensaje = estadoMensaje;
    }

    public Postulacion getPostulacion() {
        return postulacion;
    }

    public void setPostulacion(Postulacion postulacion) {
        this.postulacion = postulacion;
    }

    public Usuario getUsuarioResponde() {
        return usuarioResponde;
    }

    public void setUsuarioResponde(Usuario usuarioResponde) {
        this.usuarioResponde = usuarioResponde;
    }
}