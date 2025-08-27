package com.procol.procolombia.entities.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Anuncio}
 */
public class AnuncioDto implements Serializable {
    private final Integer id;
    private final Integer vacantesId;
    @NotNull
    @Size(max = 200)
    private final String nombrePublicoAnuncio;
    @NotNull
    @Size(max = 200)
    private final String nombrePrivadoAnuncio;
    @NotNull
    @Size(max = 50)
    private final String tipoAnuncio;
    @NotNull
    @Size(max = 50)
    private final String tamanioAnuncio;

    public AnuncioDto(Integer id, Integer vacantesId, String nombrePublicoAnuncio, String nombrePrivadoAnuncio, String tipoAnuncio, String tamanioAnuncio) {
        this.id = id;
        this.vacantesId = vacantesId;
        this.nombrePublicoAnuncio = nombrePublicoAnuncio;
        this.nombrePrivadoAnuncio = nombrePrivadoAnuncio;
        this.tipoAnuncio = tipoAnuncio;
        this.tamanioAnuncio = tamanioAnuncio;
    }

    public Integer getId() {
        return id;
    }

    public Integer getVacantesId() {
        return vacantesId;
    }

    public String getNombrePublicoAnuncio() {
        return nombrePublicoAnuncio;
    }

    public String getNombrePrivadoAnuncio() {
        return nombrePrivadoAnuncio;
    }

    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public String getTamanioAnuncio() {
        return tamanioAnuncio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnuncioDto entity = (AnuncioDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.vacantesId, entity.vacantesId) &&
                Objects.equals(this.nombrePublicoAnuncio, entity.nombrePublicoAnuncio) &&
                Objects.equals(this.nombrePrivadoAnuncio, entity.nombrePrivadoAnuncio) &&
                Objects.equals(this.tipoAnuncio, entity.tipoAnuncio) &&
                Objects.equals(this.tamanioAnuncio, entity.tamanioAnuncio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vacantesId, nombrePublicoAnuncio, nombrePrivadoAnuncio, tipoAnuncio, tamanioAnuncio);
    }
}