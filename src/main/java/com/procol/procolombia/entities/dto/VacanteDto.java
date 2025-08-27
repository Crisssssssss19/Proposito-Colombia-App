package com.procol.procolombia.entities.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.procol.procolombia.entities.Vacante}
 */
public class VacanteDto implements Serializable {
    private final Integer id;
    private final Integer idUbicacion;
    @NotNull
    @Size(max = 300)
    private final String tituloVacante;
    @NotNull
    private final String detalleVacante;
    @NotNull
    private final Instant fechaInicioVacante;
    @NotNull
    private final Instant fechaFinVacante;
    @NotNull
    private final Short estadoVacante;
    private final Integer anuncioId;
    private final Set<Integer> historialEstadosVacanteIds;
    private final Set<Integer> postulacioneIds;
    private final Set<Integer> palabrasClaveIds;
    private final Set<Integer> requisitoIds;

    public VacanteDto(Integer id, Integer idUbicacionId, String tituloVacante, String detalleVacante, Instant fechaInicioVacante, Instant fechaFinVacante, Short estadoVacante, Integer anuncioId, Set<Integer> historialEstadosVacanteIds, Set<Integer> postulacioneIds, Set<Integer> palabrasClaveIds, Set<Integer> requisitoIds) {
        this.id = id;
        this.idUbicacion = idUbicacionId;
        this.tituloVacante = tituloVacante;
        this.detalleVacante = detalleVacante;
        this.fechaInicioVacante = fechaInicioVacante;
        this.fechaFinVacante = fechaFinVacante;
        this.estadoVacante = estadoVacante;
        this.anuncioId = anuncioId;
        this.historialEstadosVacanteIds = historialEstadosVacanteIds;
        this.postulacioneIds = postulacioneIds;
        this.palabrasClaveIds = palabrasClaveIds;
        this.requisitoIds = requisitoIds;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public String getDetalleVacante() {
        return detalleVacante;
    }

    public Instant getFechaInicioVacante() {
        return fechaInicioVacante;
    }

    public Instant getFechaFinVacante() {
        return fechaFinVacante;
    }

    public Short getEstadoVacante() {
        return estadoVacante;
    }

    public Integer getAnuncioId() {
        return anuncioId;
    }

    public Set<Integer> getHistorialEstadosVacanteIds() {
        return historialEstadosVacanteIds;
    }

    public Set<Integer> getPostulacioneIds() {
        return postulacioneIds;
    }

    public Set<Integer> getPalabrasClaveIds() {
        return palabrasClaveIds;
    }

    public Set<Integer> getRequisitoIds() {
        return requisitoIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacanteDto entity = (VacanteDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idUbicacion, entity.idUbicacion) &&
                Objects.equals(this.tituloVacante, entity.tituloVacante) &&
                Objects.equals(this.detalleVacante, entity.detalleVacante) &&
                Objects.equals(this.fechaInicioVacante, entity.fechaInicioVacante) &&
                Objects.equals(this.fechaFinVacante, entity.fechaFinVacante) &&
                Objects.equals(this.estadoVacante, entity.estadoVacante) &&
                Objects.equals(this.anuncioId, entity.anuncioId) &&
                Objects.equals(this.historialEstadosVacanteIds, entity.historialEstadosVacanteIds) &&
                Objects.equals(this.postulacioneIds, entity.postulacioneIds) &&
                Objects.equals(this.palabrasClaveIds, entity.palabrasClaveIds) &&
                Objects.equals(this.requisitoIds, entity.requisitoIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUbicacion, tituloVacante, detalleVacante, fechaInicioVacante, fechaFinVacante, estadoVacante, anuncioId, historialEstadosVacanteIds, postulacioneIds, palabrasClaveIds, requisitoIds);
    }
}