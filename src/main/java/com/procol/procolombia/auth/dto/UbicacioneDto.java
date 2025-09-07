package com.procol.procolombia.auth.dto;

import com.procol.procolombia.auth.entities.Ubicacione;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Ubicacione}
 */
public class UbicacioneDto implements Serializable {
    private Integer id;
    private Integer idPadreUbicacion;
    @NotNull
    @Size(max = 150)
    private String nombreUbicacion;
    @NotNull
    @Size(max = 30)
    private String idDaneUbicacion;
    @NotNull
    @Size(max = 30)
    private String longitudUbicacion;
    @NotNull
    @Size(max = 30)
    private String latitudUbicacion;
    private Set<Integer> ubicacioneIds;
    private Set<Integer> usuarioIds;
    private Set<Integer> vacanteIds;

    public UbicacioneDto(Integer id, Integer idPadreUbicacionId, String nombreUbicacion, String idDaneUbicacion, String longitudUbicacion, String latitudUbicacion, Set<Integer> ubicacioneIds, Set<Integer> usuarioIds, Set<Integer> vacanteIds) {
        this.id = id;
        this.idPadreUbicacion = idPadreUbicacionId;
        this.nombreUbicacion = nombreUbicacion;
        this.idDaneUbicacion = idDaneUbicacion;
        this.longitudUbicacion = longitudUbicacion;
        this.latitudUbicacion = latitudUbicacion;
        this.ubicacioneIds = ubicacioneIds;
        this.usuarioIds = usuarioIds;
        this.vacanteIds = vacanteIds;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdPadreUbicacion() {
        return idPadreUbicacion;
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

    public Set<Integer> getUbicacioneIds() {
        return ubicacioneIds;
    }

    public Set<Integer> getUsuarioIds() {
        return usuarioIds;
    }

    public Set<Integer> getVacanteIds() {
        return vacanteIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UbicacioneDto entity = (UbicacioneDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idPadreUbicacion, entity.idPadreUbicacion) &&
                Objects.equals(this.nombreUbicacion, entity.nombreUbicacion) &&
                Objects.equals(this.idDaneUbicacion, entity.idDaneUbicacion) &&
                Objects.equals(this.longitudUbicacion, entity.longitudUbicacion) &&
                Objects.equals(this.latitudUbicacion, entity.latitudUbicacion) &&
                Objects.equals(this.ubicacioneIds, entity.ubicacioneIds) &&
                Objects.equals(this.usuarioIds, entity.usuarioIds) &&
                Objects.equals(this.vacanteIds, entity.vacanteIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPadreUbicacion, nombreUbicacion, idDaneUbicacion, longitudUbicacion, latitudUbicacion, ubicacioneIds, usuarioIds, vacanteIds);
    }
}