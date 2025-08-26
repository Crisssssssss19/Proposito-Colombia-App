package com.procol.procolombia.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "archivos")
public class Archivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo")
    private Integer idArchivo;

    @Column(name = "nombre_publico_archivo", nullable = false, length = 200)
    private String nombrePublicoArchivo;

    @Column(name = "nombre_archivo_archivo", nullable = false, length = 200)
    private String nombreArchivoArchivo;

    @Column(name = "tipo_archivo", nullable = false, length = 50)
    private String tipoArchivo;

    @Column(name = "tamanio_archivo", nullable = false, length = 50)
    private String tamanioArchivo;

    @Column(name = "grupo_archivo", nullable = false)
    private Short grupoArchivo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public Archivo(){
    }

    public Integer getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}