package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Anuncio}
 */
public class AnuncioDto implements Serializable {
    private final Integer idVacante;
    private final String nombrePublicoAnuncio;
    private final String nombrePrivadoAnuncio;
    private final String tipoAnuncio;
    private final String tamanioAnuncio;
    private final Integer vacanteIdVacante;

    public AnuncioDto(Integer idVacante, String nombrePublicoAnuncio, String nombrePrivadoAnuncio, String tipoAnuncio, String tamanioAnuncio, Integer vacanteIdVacante) {
        this.idVacante = idVacante;
        this.nombrePublicoAnuncio = nombrePublicoAnuncio;
        this.nombrePrivadoAnuncio = nombrePrivadoAnuncio;
        this.tipoAnuncio = tipoAnuncio;
        this.tamanioAnuncio = tamanioAnuncio;
        this.vacanteIdVacante = vacanteIdVacante;
    }

    public Integer getIdVacante() {
        return idVacante;
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

    public Integer getVacanteIdVacante() {
        return vacanteIdVacante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnuncioDto entity = (AnuncioDto) o;
        return Objects.equals(this.idVacante, entity.idVacante) &&
                Objects.equals(this.nombrePublicoAnuncio, entity.nombrePublicoAnuncio) &&
                Objects.equals(this.nombrePrivadoAnuncio, entity.nombrePrivadoAnuncio) &&
                Objects.equals(this.tipoAnuncio, entity.tipoAnuncio) &&
                Objects.equals(this.tamanioAnuncio, entity.tamanioAnuncio) &&
                Objects.equals(this.vacanteIdVacante, entity.vacanteIdVacante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVacante, nombrePublicoAnuncio, nombrePrivadoAnuncio, tipoAnuncio, tamanioAnuncio, vacanteIdVacante);
    }
}