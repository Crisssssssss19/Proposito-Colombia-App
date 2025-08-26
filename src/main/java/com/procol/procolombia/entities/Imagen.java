package com.procol.procolombia.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "imagenes")
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Integer idImagen;

    @Column(name = "nombre_publico_imagen", nullable = false, length = 200)
    private String nombrePublicoImagen;

    @Column(name = "nombre_privado_imagen", nullable = false, length = 200)
    private String nombrePrivadoImagen;

    @Column(name = "tipo_imagen", nullable = false, length = 50)
    private String tipoImagen;

    @Column(name = "tamanio_imagen", nullable = false, length = 50)
    private String tamanioImagen;

    @Column(name = "favorita_imagen", nullable = false)
    private Short favoritaImagen = 1;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public Imagen(){}

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public String getNombrePublicoImagen() {
        return nombrePublicoImagen;
    }

    public void setNombrePublicoImagen(String nombrePublicoImagen) {
        this.nombrePublicoImagen = nombrePublicoImagen;
    }

    public String getNombrePrivadoImagen() {
        return nombrePrivadoImagen;
    }

    public void setNombrePrivadoImagen(String nombrePrivadoImagen) {
        this.nombrePrivadoImagen = nombrePrivadoImagen;
    }

    public String getTipoImagen() {
        return tipoImagen;
    }

    public void setTipoImagen(String tipoImagen) {
        this.tipoImagen = tipoImagen;
    }

    public String getTamanioImagen() {
        return tamanioImagen;
    }

    public void setTamanioImagen(String tamanioImagen) {
        this.tamanioImagen = tamanioImagen;
    }

    public Short getFavoritaImagen() {
        return favoritaImagen;
    }

    public void setFavoritaImagen(Short favoritaImagen) {
        this.favoritaImagen = favoritaImagen;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}