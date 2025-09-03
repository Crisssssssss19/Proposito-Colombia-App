package com.procol.procolombia.auth.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "imagenes")
public class Imagene {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imagenes_id_gen")
    @SequenceGenerator(name = "imagenes_id_gen", sequenceName = "imagenes_id_imagen_seq", allocationSize = 1)
    @Column(name = "id_imagen", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @Size(max = 200)
    @NotNull
    @Column(name = "nombre_publico_imagen", nullable = false, length = 200)
    private String nombrePublicoImagen;

    @Size(max = 200)
    @NotNull
    @Column(name = "nombre_privado_imagen", nullable = false, length = 200)
    private String nombrePrivadoImagen;

    @Size(max = 50)
    @NotNull
    @Column(name = "tipo_imagen", nullable = false, length = 50)
    private String tipoImagen;

    @Size(max = 50)
    @NotNull
    @Column(name = "tamanio_imagen", nullable = false, length = 50)
    private String tamanioImagen;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "favorita_imagen", nullable = false)
    private Short favoritaImagen;

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

}