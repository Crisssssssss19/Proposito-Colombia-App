package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Mensaje}
 */
public class MensajeDto implements Serializable {
    private final Integer idMensaje;
    private final String textoMensaje;
    private final Date fechaMensaje;
    private final Short estadoMensaje;
    private final Integer idPostulacion;
    private final Integer idUsuario;

    public MensajeDto(Integer idMensaje, String textoMensaje, Date fechaMensaje, Short estadoMensaje, Integer postulacionIdPostulacion, Integer usuarioRespondeIdUsuario) {
        this.idMensaje = idMensaje;
        this.textoMensaje = textoMensaje;
        this.fechaMensaje = fechaMensaje;
        this.estadoMensaje = estadoMensaje;
        this.idPostulacion = postulacionIdPostulacion;
        this.idUsuario = usuarioRespondeIdUsuario;
    }

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public String getTextoMensaje() {
        return textoMensaje;
    }

    public Date getFechaMensaje() {
        return fechaMensaje;
    }

    public Short getEstadoMensaje() {
        return estadoMensaje;
    }

    public Integer getIdPostulacion() {
        return idPostulacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MensajeDto entity = (MensajeDto) o;
        return Objects.equals(this.idMensaje, entity.idMensaje) &&
                Objects.equals(this.textoMensaje, entity.textoMensaje) &&
                Objects.equals(this.fechaMensaje, entity.fechaMensaje) &&
                Objects.equals(this.estadoMensaje, entity.estadoMensaje) &&
                Objects.equals(this.idPostulacion, entity.idPostulacion) &&
                Objects.equals(this.idUsuario, entity.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMensaje, textoMensaje, fechaMensaje, estadoMensaje, idPostulacion, idUsuario);
    }
}