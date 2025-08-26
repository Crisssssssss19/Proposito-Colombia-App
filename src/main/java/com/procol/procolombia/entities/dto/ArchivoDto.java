package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Archivo}
 */
public class ArchivoDto implements Serializable {
    private final Integer idArchivo;
    private final String nombrePublicoArchivo;
    private final String nombreArchivoArchivo;
    private final String tipoArchivo;
    private final String tamanioArchivo;
    private final Short grupoArchivo;
    private final Integer usuarioIdUsuario;

    public ArchivoDto(Integer idArchivo, String nombrePublicoArchivo, String nombreArchivoArchivo, String tipoArchivo, String tamanioArchivo, Short grupoArchivo, Integer usuarioIdUsuario) {
        this.idArchivo = idArchivo;
        this.nombrePublicoArchivo = nombrePublicoArchivo;
        this.nombreArchivoArchivo = nombreArchivoArchivo;
        this.tipoArchivo = tipoArchivo;
        this.tamanioArchivo = tamanioArchivo;
        this.grupoArchivo = grupoArchivo;
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public Integer getIdArchivo() {
        return idArchivo;
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

    public Integer getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArchivoDto entity = (ArchivoDto) o;
        return Objects.equals(this.idArchivo, entity.idArchivo) &&
                Objects.equals(this.nombrePublicoArchivo, entity.nombrePublicoArchivo) &&
                Objects.equals(this.nombreArchivoArchivo, entity.nombreArchivoArchivo) &&
                Objects.equals(this.tipoArchivo, entity.tipoArchivo) &&
                Objects.equals(this.tamanioArchivo, entity.tamanioArchivo) &&
                Objects.equals(this.grupoArchivo, entity.grupoArchivo) &&
                Objects.equals(this.usuarioIdUsuario, entity.usuarioIdUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArchivo, nombrePublicoArchivo, nombreArchivoArchivo, tipoArchivo, tamanioArchivo, grupoArchivo, usuarioIdUsuario);
    }
}