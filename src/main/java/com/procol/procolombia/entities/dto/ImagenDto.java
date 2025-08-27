package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Imagen}
 */
public class ImagenDto implements Serializable {
    private final Integer idImagen;
    private final String nombrePublicoImagen;
    private final String nombrePrivadoImagen;
    private final String tipoImagen;
    private final String tamanioImagen;
    private final Short favoritaImagen;
    private final Integer idUsuario;

    public ImagenDto(Integer idImagen, String nombrePublicoImagen, String nombrePrivadoImagen, String tipoImagen, String tamanioImagen, Short favoritaImagen, Integer usuarioIdUsuario) {
        this.idImagen = idImagen;
        this.nombrePublicoImagen = nombrePublicoImagen;
        this.nombrePrivadoImagen = nombrePrivadoImagen;
        this.tipoImagen = tipoImagen;
        this.tamanioImagen = tamanioImagen;
        this.favoritaImagen = favoritaImagen;
        this.idUsuario = usuarioIdUsuario;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public String getNombrePublicoImagen() {
        return nombrePublicoImagen;
    }

    public String getNombrePrivadoImagen() {
        return nombrePrivadoImagen;
    }

    public String getTipoImagen() {
        return tipoImagen;
    }

    public String getTamanioImagen() {
        return tamanioImagen;
    }

    public Short getFavoritaImagen() {
        return favoritaImagen;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagenDto entity = (ImagenDto) o;
        return Objects.equals(this.idImagen, entity.idImagen) &&
                Objects.equals(this.nombrePublicoImagen, entity.nombrePublicoImagen) &&
                Objects.equals(this.nombrePrivadoImagen, entity.nombrePrivadoImagen) &&
                Objects.equals(this.tipoImagen, entity.tipoImagen) &&
                Objects.equals(this.tamanioImagen, entity.tamanioImagen) &&
                Objects.equals(this.favoritaImagen, entity.favoritaImagen) &&
                Objects.equals(this.idUsuario, entity.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idImagen, nombrePublicoImagen, nombrePrivadoImagen, tipoImagen, tamanioImagen, favoritaImagen, idUsuario);
    }
}