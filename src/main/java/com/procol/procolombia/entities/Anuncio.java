package com.procol.procolombia.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "anuncios")
public class Anuncio {

    @Id
    @Column(name = "id_vacante")
    private Integer idVacante;

    @Column(name = "nombre_publico_anuncio", nullable = false, length = 200)
    private String nombrePublicoAnuncio;

    @Column(name = "nombre_privado_anuncio", nullable = false, length = 200)
    private String nombrePrivadoAnuncio;

    @Column(name = "tipo_anuncio", nullable = false, length = 50)
    private String tipoAnuncio;

    @Column(name = "tamanio_anuncio", nullable = false, length = 50)
    private String tamanioAnuncio;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_vacante")
    private Vacante vacante;

    public Anuncio() {
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
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

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }
}
