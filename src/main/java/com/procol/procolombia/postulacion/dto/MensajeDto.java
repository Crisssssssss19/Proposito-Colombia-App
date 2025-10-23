package com.procol.procolombia.postulacion.dto;

import com.procol.procolombia.postulacion.entities.Mensaje;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link Mensaje}
 */
public class MensajeDto implements Serializable {
    private Integer id;
    private Integer idPostulacion;
    private Integer idUsuarioResponde;
    @NotNull
    private String textoMensaje;
    @NotNull
    private Instant fechaMensaje;
    @NotNull
    private Short estadoMensaje;

    public MensajeDto() {
    }

    public MensajeDto(Integer id, Integer idPostulacionId, Integer idUsuarioRespondeId, String textoMensaje, Instant fechaMensaje, Short estadoMensaje) {
        this.id = id;
        this.idPostulacion = idPostulacionId;
        this.idUsuarioResponde = idUsuarioRespondeId;
        this.textoMensaje = textoMensaje;
        this.fechaMensaje = fechaMensaje;
        this.estadoMensaje = estadoMensaje;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdPostulacion() {
        return idPostulacion;
    }

    public Integer getIdUsuarioResponde() {
        return idUsuarioResponde;
    }

    public String getTextoMensaje() {
        return textoMensaje;
    }

    public Instant getFechaMensaje() {
        return fechaMensaje;
    }

    public Short getEstadoMensaje() {
        return estadoMensaje;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdPostulacion(Integer idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public void setIdUsuarioResponde(Integer idUsuarioResponde) {
        this.idUsuarioResponde = idUsuarioResponde;
    }

    public void setTextoMensaje(@NotNull String textoMensaje) {
        this.textoMensaje = textoMensaje;
    }

    public void setFechaMensaje(@NotNull Instant fechaMensaje) {
        this.fechaMensaje = fechaMensaje;
    }

    public void setEstadoMensaje(@NotNull Short estadoMensaje) {
        this.estadoMensaje = estadoMensaje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MensajeDto entity = (MensajeDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idPostulacion, entity.idPostulacion) &&
                Objects.equals(this.idUsuarioResponde, entity.idUsuarioResponde) &&
                Objects.equals(this.textoMensaje, entity.textoMensaje) &&
                Objects.equals(this.fechaMensaje, entity.fechaMensaje) &&
                Objects.equals(this.estadoMensaje, entity.estadoMensaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPostulacion, idUsuarioResponde, textoMensaje, fechaMensaje, estadoMensaje);
    }
}