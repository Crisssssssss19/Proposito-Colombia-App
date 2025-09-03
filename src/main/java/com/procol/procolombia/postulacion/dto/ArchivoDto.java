package com.procol.procolombia.postulacion.dto;

import com.procol.procolombia.postulacion.entities.Archivo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Archivo}
 */
public class ArchivoDto implements Serializable {
    private final Integer id;
    private final Integer idUsuario;
    @NotNull
    @Size(max = 200)
    private final String nombrePublicoArchivo;
    @NotNull
    @Size(max = 200)
    private final String nombreArchivoArchivo;
    @NotNull
    @Size(max = 50)
    private final String tipoArchivo;
    @NotNull
    @Size(max = 50)
    private final String tamanioArchivo;
    @NotNull
    private final Short grupoArchivo;

    public ArchivoDto(Integer id, Integer idUsuarioId, String nombrePublicoArchivo, String nombreArchivoArchivo, String tipoArchivo, String tamanioArchivo, Short grupoArchivo) {
        this.id = id;
        this.idUsuario = idUsuarioId;
        this.nombrePublicoArchivo = nombrePublicoArchivo;
        this.nombreArchivoArchivo = nombreArchivoArchivo;
        this.tipoArchivo = tipoArchivo;
        this.tamanioArchivo = tamanioArchivo;
        this.grupoArchivo = grupoArchivo;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getNombrePublicoArchivo() {
        return nombrePublicoArchivo;
    }

    public String getNombreArchivoArchivo() {
        return nombreArchivoArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public String getTamanioArchivo() {
        return tamanioArchivo;
    }

    public Short getGrupoArchivo() {
        return grupoArchivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArchivoDto entity = (ArchivoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.nombrePublicoArchivo, entity.nombrePublicoArchivo) &&
                Objects.equals(this.nombreArchivoArchivo, entity.nombreArchivoArchivo) &&
                Objects.equals(this.tipoArchivo, entity.tipoArchivo) &&
                Objects.equals(this.tamanioArchivo, entity.tamanioArchivo) &&
                Objects.equals(this.grupoArchivo, entity.grupoArchivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUsuario, nombrePublicoArchivo, nombreArchivoArchivo, tipoArchivo, tamanioArchivo, grupoArchivo);
    }
}