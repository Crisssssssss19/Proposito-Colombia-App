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
    private final Integer id;
    private final Integer idPostulacion;
    private final Integer idUsuarioResponde;
    @NotNull
    private final String textoMensaje;
    @NotNull
    private final Instant fechaMensaje;
    @NotNull
    private final Short estadoMensaje;

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