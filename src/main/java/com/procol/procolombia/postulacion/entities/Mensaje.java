package com.procol.procolombia.postulacion.entities;

import com.procol.procolombia.auth.entities.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "mensajes")
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mensajes_id_gen")
    @SequenceGenerator(name = "mensajes_id_gen", sequenceName = "mensajes_id_mensaje_seq", allocationSize = 1)
    @Column(name = "id_mensaje", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_postulacion", nullable = false)
    private Postulacione idPostulacion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_usuario_responde", nullable = false)
    private Usuario idUsuarioResponde;

    @NotNull
    @Column(name = "texto_mensaje", nullable = false, length = Integer.MAX_VALUE)
    private String textoMensaje;

    @NotNull
    @Column(name = "fecha_mensaje", nullable = false)
    private Instant fechaMensaje;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "estado_mensaje", nullable = false)
    private Short estadoMensaje;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Postulacione getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Postulacione idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public Usuario getIdUsuarioResponde() {
        return idUsuarioResponde;
    }

    public void setIdUsuarioResponde(Usuario idUsuarioResponde) {
        this.idUsuarioResponde = idUsuarioResponde;
    }

    public String getTextoMensaje() {
        return textoMensaje;
    }

    public void setTextoMensaje(String textoMensaje) {
        this.textoMensaje = textoMensaje;
    }

    public Instant getFechaMensaje() {
        return fechaMensaje;
    }

    public void setFechaMensaje(Instant fechaMensaje) {
        this.fechaMensaje = fechaMensaje;
    }

    public Short getEstadoMensaje() {
        return estadoMensaje;
    }

    public void setEstadoMensaje(Short estadoMensaje) {
        this.estadoMensaje = estadoMensaje;
    }

}