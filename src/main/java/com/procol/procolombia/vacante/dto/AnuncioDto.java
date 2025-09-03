package com.procol.procolombia.vacante.dto;

import com.procol.procolombia.vacante.entities.Anuncio;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Anuncio}
 */
public class AnuncioDto implements Serializable {
    private Integer id;
    private Integer vacantesId;
    @NotNull
    @Size(max = 200)
    private String nombrePublicoAnuncio;
    @NotNull
    @Size(max = 200)
    private String nombrePrivadoAnuncio;
    @NotNull
    @Size(max = 50)
    private String tipoAnuncio;
    @NotNull
    @Size(max = 50)
    private String tamanioAnuncio;

    public AnuncioDto() {
    }

    public AnuncioDto(Integer id, Integer vacantesId, String nombrePublicoAnuncio, String nombrePrivadoAnuncio, String tipoAnuncio, String tamanioAnuncio){
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVacantesId(Integer vacantesId) {
        this.vacantesId = vacantesId;
    }

    public void setNombrePublicoAnuncio(@NotNull @Size(max = 200) String nombrePublicoAnuncio) {
        this.nombrePublicoAnuncio = nombrePublicoAnuncio;
    }

    public void setNombrePrivadoAnuncio(@NotNull @Size(max = 200) String nombrePrivadoAnuncio) {
        this.nombrePrivadoAnuncio = nombrePrivadoAnuncio;
    }

    public void setTipoAnuncio(@NotNull @Size(max = 50) String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public void setTamanioAnuncio(@NotNull @Size(max = 50) String tamanioAnuncio) {
        this.tamanioAnuncio = tamanioAnuncio;
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