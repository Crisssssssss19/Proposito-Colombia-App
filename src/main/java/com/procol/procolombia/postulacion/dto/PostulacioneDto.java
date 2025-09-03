package com.procol.procolombia.postulacion.dto;

import com.procol.procolombia.postulacion.entities.Postulacione;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Postulacione}
 */
public class PostulacioneDto implements Serializable {
    private final Integer id;
    private final Integer idVacante;
    private final Integer idUsuario;
    private final Short idUsuarioEstadoUsuario;
    @NotNull
    private final Instant fechaPostulacion;
    @NotNull
    private final Short correspondenciaPostulacion;
    @NotNull
    private final Short estadoPostulacion;
    private final Set<Integer> historialEstadosPostulacioneIds;
    private final Set<Integer> mensajeIds;

    public PostulacioneDto(Integer id, Integer idVacanteId, Integer idUsuarioId, Short idUsuarioEstadoUsuario, Instant fechaPostulacion, Short correspondenciaPostulacion, Short estadoPostulacion, Set<Integer> historialEstadosPostulacioneIds, Set<Integer> mensajeIds) {
        this.id = id;
        this.idVacante = idVacanteId;
        this.idUsuario = idUsuarioId;
        this.idUsuarioEstadoUsuario = idUsuarioEstadoUsuario;
        this.fechaPostulacion = fechaPostulacion;
        this.correspondenciaPostulacion = correspondenciaPostulacion;
        this.estadoPostulacion = estadoPostulacion;
        this.historialEstadosPostulacioneIds = historialEstadosPostulacioneIds;
        this.mensajeIds = mensajeIds;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Short getIdUsuarioEstadoUsuario() {
        return idUsuarioEstadoUsuario;
    }

    public Instant getFechaPostulacion() {
        return fechaPostulacion;
    }

    public Short getCorrespondenciaPostulacion() {
        return correspondenciaPostulacion;
    }

    public Short getEstadoPostulacion() {
        return estadoPostulacion;
    }

    public Set<Integer> getHistorialEstadosPostulacioneIds() {
        return historialEstadosPostulacioneIds;
    }

    public Set<Integer> getMensajeIds() {
        return mensajeIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostulacioneDto entity = (PostulacioneDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idVacante, entity.idVacante) &&
                Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.idUsuarioEstadoUsuario, entity.idUsuarioEstadoUsuario) &&
                Objects.equals(this.fechaPostulacion, entity.fechaPostulacion) &&
                Objects.equals(this.correspondenciaPostulacion, entity.correspondenciaPostulacion) &&
                Objects.equals(this.estadoPostulacion, entity.estadoPostulacion) &&
                Objects.equals(this.historialEstadosPostulacioneIds, entity.historialEstadosPostulacioneIds) &&
                Objects.equals(this.mensajeIds, entity.mensajeIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idVacante, idUsuario, idUsuarioEstadoUsuario, fechaPostulacion, correspondenciaPostulacion, estadoPostulacion, historialEstadosPostulacioneIds, mensajeIds);
    }
}