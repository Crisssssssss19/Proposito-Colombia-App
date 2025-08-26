package com.procol.procolombia.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "ubicaciones")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion")
    private Integer idUbicacion;

    @Column(name = "nombre_ubicacion", nullable = false, length = 150)
    private String nombreUbicacion;

    @Column(name = "id_dane_ubicacion", nullable = false, length = 30)
    private String idDaneUbicacion;

    @Column(name = "longitud_ubicacion", nullable = false, length = 30)
    private String longitudUbicacion;

    @Column(name = "latitud_ubicacion", nullable = false, length = 30)
    private String latitudUbicacion;

    @ManyToOne
    @JoinColumn(name = "id_padre_ubicacion")
    private Ubicacion ubicacionPadre;

    @OneToMany(mappedBy = "ubicacionPadre")
    private List<Ubicacion> subUbicaciones;

    @OneToMany(mappedBy = "ubicacion")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "ubicacion")
    private List<Vacante> vacantes;

    public Ubicacion(){}

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public void setNombreUbicacion(String nombreUbicacion) {
        this.nombreUbicacion = nombreUbicacion;
    }

    public String getIdDaneUbicacion() {
        return idDaneUbicacion;
    }

    public void setIdDaneUbicacion(String idDaneUbicacion) {
        this.idDaneUbicacion = idDaneUbicacion;
    }

    public String getLongitudUbicacion() {
        return longitudUbicacion;
    }

    public void setLongitudUbicacion(String longitudUbicacion) {
        this.longitudUbicacion = longitudUbicacion;
    }

    public String getLatitudUbicacion() {
        return latitudUbicacion;
    }

    public void setLatitudUbicacion(String latitudUbicacion) {
        this.latitudUbicacion = latitudUbicacion;
    }

    public Ubicacion getUbicacionPadre() {
        return ubicacionPadre;
    }

    public void setUbicacionPadre(Ubicacion ubicacionPadre) {
        this.ubicacionPadre = ubicacionPadre;
    }

    public List<Ubicacion> getSubUbicaciones() {
        return subUbicaciones;
    }

    public void setSubUbicaciones(List<Ubicacion> subUbicaciones) {
        this.subUbicaciones = subUbicaciones;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(List<Vacante> vacantes) {
        this.vacantes = vacantes;
    }
}