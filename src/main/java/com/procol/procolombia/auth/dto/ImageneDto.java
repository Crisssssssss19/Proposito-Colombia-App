package com.procol.procolombia.auth.dto;

import com.procol.procolombia.auth.entities.Imagene;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Imagene}
 */
public class ImageneDto implements Serializable {
    private Integer id;
    private Integer idUsuario;
    @NotNull
    @Size(max = 200)
    private String nombrePublicoImagen;
    @NotNull
    @Size(max = 200)
    private String nombrePrivadoImagen;
    @NotNull
    @Size(max = 50)
    private String tipoImagen;
    @NotNull
    @Size(max = 50)
    private String tamanioImagen;
    @NotNull
    private Short favoritaImagen;

    public ImageneDto(Integer id, Integer idUsuarioId, String nombrePublicoImagen, String nombrePrivadoImagen, String tipoImagen, String tamanioImagen, Short favoritaImagen) {
        this.id = id;
        this.idUsuario = idUsuarioId;
        this.nombrePublicoImagen = nombrePublicoImagen;
        this.nombrePrivadoImagen = nombrePrivadoImagen;
        this.tipoImagen = tipoImagen;
        this.tamanioImagen = tamanioImagen;
        this.favoritaImagen = favoritaImagen;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageneDto entity = (ImageneDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.nombrePublicoImagen, entity.nombrePublicoImagen) &&
                Objects.equals(this.nombrePrivadoImagen, entity.nombrePrivadoImagen) &&
                Objects.equals(this.tipoImagen, entity.tipoImagen) &&
                Objects.equals(this.tamanioImagen, entity.tamanioImagen) &&
                Objects.equals(this.favoritaImagen, entity.favoritaImagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUsuario, nombrePublicoImagen, nombrePrivadoImagen, tipoImagen, tamanioImagen, favoritaImagen);
    }
}