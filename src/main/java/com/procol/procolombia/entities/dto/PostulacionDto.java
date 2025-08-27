package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Postulacion}
 */
public class PostulacionDto implements Serializable {
    private final Integer idPostulacion;
    private final Date fechaPostulacion;
    private final Short correspondenciaPostulacion;
    private final Short estadoPostulacion;
    private final Integer vacanteIdVacante;
    private final Date vacanteFechaFinVacante;
    private final Integer idUsuario;
    private final List<Integer> idMensajes;
    private final List<Integer> idHistorialPostulacions;

    public PostulacionDto(Integer idPostulacion, Date fechaPostulacion, Short correspondenciaPostulacion, Short estadoPostulacion, Integer vacanteIdVacante, Date vacanteFechaFinVacante, Integer usuarioIdUsuario, List<Integer> mensajeIdMensajes, List<Integer> historialIdHistorialPostulacions) {
        this.idPostulacion = idPostulacion;
        this.fechaPostulacion = fechaPostulacion;
        this.correspondenciaPostulacion = correspondenciaPostulacion;
        this.estadoPostulacion = estadoPostulacion;
        this.vacanteIdVacante = vacanteIdVacante;
        this.vacanteFechaFinVacante = vacanteFechaFinVacante;
        this.idUsuario = usuarioIdUsuario;
        this.idMensajes = mensajeIdMensajes;
        this.idHistorialPostulacions = historialIdHistorialPostulacions;
    }

    public Integer getIdPostulacion() {
        return idPostulacion;
    }

    public Date getFechaPostulacion() {
        return fechaPostulacion;
    }

    public Short getCorrespondenciaPostulacion() {
        return correspondenciaPostulacion;
    }

    public Short getEstadoPostulacion() {
        return estadoPostulacion;
    }

    public Integer getVacanteIdVacante() {
        return vacanteIdVacante;
    }

    public Date getVacanteFechaFinVacante() {
        return vacanteFechaFinVacante;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public List<Integer> getIdMensajes() {
        return idMensajes;
    }

    public List<Integer> getIdHistorialPostulacions() {
        return idHistorialPostulacions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostulacionDto entity = (PostulacionDto) o;
        return Objects.equals(this.idPostulacion, entity.idPostulacion) &&
                Objects.equals(this.fechaPostulacion, entity.fechaPostulacion) &&
                Objects.equals(this.correspondenciaPostulacion, entity.correspondenciaPostulacion) &&
                Objects.equals(this.estadoPostulacion, entity.estadoPostulacion) &&
                Objects.equals(this.vacanteIdVacante, entity.vacanteIdVacante) &&
                Objects.equals(this.vacanteFechaFinVacante, entity.vacanteFechaFinVacante) &&
                Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.idMensajes, entity.idMensajes) &&
                Objects.equals(this.idHistorialPostulacions, entity.idHistorialPostulacions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPostulacion, fechaPostulacion, correspondenciaPostulacion, estadoPostulacion, vacanteIdVacante, vacanteFechaFinVacante, idUsuario, idMensajes, idHistorialPostulacions);
    }
}