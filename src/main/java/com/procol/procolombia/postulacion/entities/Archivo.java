package com.procol.procolombia.postulacion.entities;

import com.procol.procolombia.auth.entities.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "archivos")
public class Archivo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "archivos_id_gen")
    @SequenceGenerator(name = "archivos_id_gen", sequenceName = "archivos_id_archivo_seq", allocationSize = 1)
    @Column(name = "id_archivo", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @Size(max = 200)
    @NotNull
    @Column(name = "nombre_publico_archivo", nullable = false, length = 200)
    private String nombrePublicoArchivo;

    @Size(max = 200)
    @NotNull
    @Column(name = "nombre_archivo_archivo", nullable = false, length = 200)
    private String nombreArchivoArchivo;

    @Size(max = 50)
    @NotNull
    @Column(name = "tipo_archivo", nullable = false, length = 50)
    private String tipoArchivo;

    @Size(max = 50)
    @NotNull
    @Column(name = "tamanio_archivo", nullable = false, length = 50)
    private String tamanioArchivo;

    @NotNull
    @Column(name = "grupo_archivo", nullable = false)
    private Short grupoArchivo;

    @NotNull
    @Column(name = "fecha_subida", nullable = false, updatable = false)
    private LocalDateTime fechaSubida;

    @PrePersist
    public void prePersist() {
        this.fechaSubida = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombrePublicoArchivo() {
        return nombrePublicoArchivo;
    }

    public void setNombrePublicoArchivo(String nombrePublicoArchivo) {
        this.nombrePublicoArchivo = nombrePublicoArchivo;
    }

    public String getNombreArchivoArchivo() {
        return nombreArchivoArchivo;
    }

    public void setNombreArchivoArchivo(String nombreArchivoArchivo) {
        this.nombreArchivoArchivo = nombreArchivoArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getTamanioArchivo() {
        return tamanioArchivo;
    }

    public void setTamanioArchivo(String tamanioArchivo) {
        this.tamanioArchivo = tamanioArchivo;
    }

    public Short getGrupoArchivo() {
        return grupoArchivo;
    }

    public void setGrupoArchivo(Short grupoArchivo) {
        this.grupoArchivo = grupoArchivo;
    }

    public LocalDateTime getFechaSubida() { return  fechaSubida; }
}