package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.lang.Integer;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Ubicacion}
 */
public class UbicacionDto implements Serializable {
    private final Integer idUbicacion;
    private final String nombreUbicacion;
    private final String idDaneUbicacion;
    private final String longitudUbicacion;
    private final String latitudUbicacion;
    private final Integer idPadreUbicacion;
    private final List<Integer> idSubUbicacione;
    private final List<Integer> idUsuarios;
    private final List<Integer> idVacantes;

    public UbicacionDto(Integer idUbicacion, String nombreUbicacion, String idDaneUbicacion, String longitudUbicacion, String latitudUbicacion, Integer ubicacionPadreIdUbicacion, List<Integer> subUbicacioneIdUbicacions, List<Integer> usuarioIdUsuarios, List<Integer> vacanteIdVacantes) {
        this.idUbicacion = idUbicacion;
        this.nombreUbicacion = nombreUbicacion;
        this.idDaneUbicacion = idDaneUbicacion;
        this.longitudUbicacion = longitudUbicacion;
        this.latitudUbicacion = latitudUbicacion;
        this.idPadreUbicacion = ubicacionPadreIdUbicacion;
        this.idSubUbicacione = subUbicacioneIdUbicacions;
        this.idUsuarios = usuarioIdUsuarios;
        this.idVacantes = vacanteIdVacantes;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public String getIdDaneUbicacion() {
        return idDaneUbicacion;
    }

    public String getLongitudUbicacion() {
        return longitudUbicacion;
    }

    public String getLatitudUbicacion() {
        return latitudUbicacion;
    }

    public Integer getIdPadreUbicacion() {
        return idPadreUbicacion;
    }

    public List<Integer> getIdSubUbicacione() {
        return idSubUbicacione;
    }

    public List<Integer> getIdUsuarios() {
        return idUsuarios;
    }

    public List<Integer> getIdVacantes() {
        return idVacantes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UbicacionDto entity = (UbicacionDto) o;
        return Objects.equals(this.idUbicacion, entity.idUbicacion) &&
                Objects.equals(this.nombreUbicacion, entity.nombreUbicacion) &&
                Objects.equals(this.idDaneUbicacion, entity.idDaneUbicacion) &&
                Objects.equals(this.longitudUbicacion, entity.longitudUbicacion) &&
                Objects.equals(this.latitudUbicacion, entity.latitudUbicacion) &&
                Objects.equals(this.idPadreUbicacion, entity.idPadreUbicacion) &&
                Objects.equals(this.idSubUbicacione, entity.idSubUbicacione) &&
                Objects.equals(this.idUsuarios, entity.idUsuarios) &&
                Objects.equals(this.idVacantes, entity.idVacantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUbicacion, nombreUbicacion, idDaneUbicacion, longitudUbicacion, latitudUbicacion, idPadreUbicacion, idSubUbicacione, idUsuarios, idVacantes);
    }
}