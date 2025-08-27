package com.procol.procolombia.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "anuncios")
public class Anuncio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacante", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_vacante", nullable = false)
    private Vacante vacantes;

    @Size(max = 200)
    @NotNull
    @Column(name = "nombre_publico_anuncio", nullable = false, length = 200)
    private String nombrePublicoAnuncio;

    @Size(max = 200)
    @NotNull
    @Column(name = "nombre_privado_anuncio", nullable = false, length = 200)
    private String nombrePrivadoAnuncio;

    @Size(max = 50)
    @NotNull
    @Column(name = "tipo_anuncio", nullable = false, length = 50)
    private String tipoAnuncio;

    @Size(max = 50)
    @NotNull
    @Column(name = "tamanio_anuncio", nullable = false, length = 50)
    private String tamanioAnuncio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vacante getVacantes() {
        return vacantes;
    }

    public void setVacantes(Vacante vacantes) {
        this.vacantes = vacantes;
    }

    public String getNombrePublicoAnuncio() {
        return nombrePublicoAnuncio;
    }

    public void setNombrePublicoAnuncio(String nombrePublicoAnuncio) {
        this.nombrePublicoAnuncio = nombrePublicoAnuncio;
    }

    public String getNombrePrivadoAnuncio() {
        return nombrePrivadoAnuncio;
    }

    public void setNombrePrivadoAnuncio(String nombrePrivadoAnuncio) {
        this.nombrePrivadoAnuncio = nombrePrivadoAnuncio;
    }

    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public String getTamanioAnuncio() {
        return tamanioAnuncio;
    }

    public void setTamanioAnuncio(String tamanioAnuncio) {
        this.tamanioAnuncio = tamanioAnuncio;
    }

}