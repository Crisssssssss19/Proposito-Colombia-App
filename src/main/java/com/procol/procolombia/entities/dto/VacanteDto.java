package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Vacante}
 */
public class VacanteDto implements Serializable {
    private final Integer idVacante;
    private final String tituloVacante;
    private final String detalleVacante;
    private final Date fechaInicioVacante;
    private final Date fechaFinVacante;
    private final Short estadoVacante;
    private final Integer idUbicacion;
    private final Integer idEmpresa;
    private final Integer idUsuario;
    private final List<Integer> idRequisitos;
    private final List<Integer> idPostulacions;
    private final List<Integer> idHistorialEstadoVacantes;
    private final Integer idAnuncioVacante;
    private final List<Integer> IdPalabraClaves;

    public VacanteDto(Integer idVacante, String tituloVacante, String detalleVacante, Date fechaInicioVacante, Date fechaFinVacante, Short estadoVacante, Integer ubicacionIdUbicacion, Integer empresaIdEmpresa, Integer usuarioIdUsuario, List<Integer> requisitoIdRequisitos, List<Integer> postulacioneIdPostulacions, List<Integer> historialIdHistorialEstadoVacantes, Integer anuncioIdVacante, List<Integer> palabrasClaveIdPalabraClaves) {
        this.idVacante = idVacante;
        this.tituloVacante = tituloVacante;
        this.detalleVacante = detalleVacante;
        this.fechaInicioVacante = fechaInicioVacante;
        this.fechaFinVacante = fechaFinVacante;
        this.estadoVacante = estadoVacante;
        this.idUbicacion = ubicacionIdUbicacion;
        this.idEmpresa = empresaIdEmpresa;
        this.idUsuario = usuarioIdUsuario;
        this.idRequisitos = requisitoIdRequisitos;
        this.idPostulacions = postulacioneIdPostulacions;
        this.idHistorialEstadoVacantes = historialIdHistorialEstadoVacantes;
        this.idAnuncioVacante = anuncioIdVacante;
        this.IdPalabraClaves = palabrasClaveIdPalabraClaves;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public String getDetalleVacante() {
        return detalleVacante;
    }

    public Date getFechaInicioVacante() {
        return fechaInicioVacante;
    }

    public Date getFechaFinVacante() {
        return fechaFinVacante;
    }

    public Short getEstadoVacante() {
        return estadoVacante;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public List<Integer> getIdRequisitos() {
        return idRequisitos;
    }

    public List<Integer> getIdPostulacions() {
        return idPostulacions;
    }

    public List<Integer> getIdHistorialEstadoVacantes() {
        return idHistorialEstadoVacantes;
    }

    public Integer getIdAnuncioVacante() {
        return idAnuncioVacante;
    }

    public List<Integer> getIdPalabraClaves() {
        return IdPalabraClaves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacanteDto entity = (VacanteDto) o;
        return Objects.equals(this.idVacante, entity.idVacante) &&
                Objects.equals(this.tituloVacante, entity.tituloVacante) &&
                Objects.equals(this.detalleVacante, entity.detalleVacante) &&
                Objects.equals(this.fechaInicioVacante, entity.fechaInicioVacante) &&
                Objects.equals(this.fechaFinVacante, entity.fechaFinVacante) &&
                Objects.equals(this.estadoVacante, entity.estadoVacante) &&
                Objects.equals(this.idUbicacion, entity.idUbicacion) &&
                Objects.equals(this.idEmpresa, entity.idEmpresa) &&
                Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.idRequisitos, entity.idRequisitos) &&
                Objects.equals(this.idPostulacions, entity.idPostulacions) &&
                Objects.equals(this.idHistorialEstadoVacantes, entity.idHistorialEstadoVacantes) &&
                Objects.equals(this.idAnuncioVacante, entity.idAnuncioVacante) &&
                Objects.equals(this.IdPalabraClaves, entity.IdPalabraClaves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVacante, tituloVacante, detalleVacante, fechaInicioVacante, fechaFinVacante, estadoVacante, idUbicacion, idEmpresa, idUsuario, idRequisitos, idPostulacions, idHistorialEstadoVacantes, idAnuncioVacante, IdPalabraClaves);
    }
}