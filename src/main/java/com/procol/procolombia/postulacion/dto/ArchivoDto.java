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
    private Integer id;
    private Integer idUsuario;
    @NotNull
    @Size(max = 200)
    private String nombrePublicoArchivo;
    @NotNull
    @Size(max = 200)
    private String nombreArchivoArchivo;
    @NotNull
    @Size(max = 50)
    private String tipoArchivo;
    @NotNull
    @Size(max = 50)
    private String tamanioArchivo;
    @NotNull
    private Short grupoArchivo;

    public ArchivoDto() {
    }

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

    public void setId(Integer id) { this.id = id; }

    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public void setNombrePublicoArchivo(String nombrePublicoArchivo) {
        this.nombrePublicoArchivo = nombrePublicoArchivo;
    }

    public void setNombreArchivoArchivo(String nombreArchivoArchivo) {
        this.nombreArchivoArchivo = nombreArchivoArchivo;
    }
    public void setTipoArchivo(String tipoArchivo) { this.tipoArchivo = tipoArchivo; }
    public void setTamanioArchivo(String tamanioArchivo) { this.tamanioArchivo = tamanioArchivo; }
    public void setGrupoArchivo(Short grupoArchivo) { this.grupoArchivo = grupoArchivo; }

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