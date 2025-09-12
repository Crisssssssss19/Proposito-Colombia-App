package com.procol.procolombia.auth.entities;

import com.procol.procolombia.vacante.entities.Vacante;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ubicaciones")
public class Ubicacione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ubicaciones_id_gen")
    @SequenceGenerator(name = "ubicaciones_id_gen", sequenceName = "ubicaciones_id_ubicacion_seq", allocationSize = 1)
    @Column(name = "id_ubicacion", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_padre_ubicacion")
    private Ubicacione idPadreUbicacion;

    @Size(max = 150)
    @NotNull
    @Column(name = "nombre_ubicacion", nullable = false, length = 150)
    private String nombreUbicacion;

    @Size(max = 30)
    @NotNull
    @Column(name = "id_dane_ubicacion", nullable = false, length = 30)
    private String idDaneUbicacion;

    @Size(max = 30)
    @NotNull
    @Column(name = "longitud_ubicacion", nullable = false, length = 30)
    private String longitudUbicacion;

    @Size(max = 30)
    @NotNull
    @Column(name = "latitud_ubicacion", nullable = false, length = 30)
    private String latitudUbicacion;

    @OneToMany(mappedBy = "idPadreUbicacion")
    private Set<Ubicacione> ubicaciones = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUbicacion")
    private Set<Usuario> usuarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUbicacion")
    private Set<Vacante> vacantes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ubicacione getIdPadreUbicacion() {
        return idPadreUbicacion;
    }

    public void setIdPadreUbicacion(Ubicacione idPadreUbicacion) {
        this.idPadreUbicacion = idPadreUbicacion;
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

    public Set<Ubicacione> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(Set<Ubicacione> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(Set<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

}