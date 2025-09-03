package com.procol.procolombia.vacante.entities;

import com.procol.procolombia.auth.entities.Ubicacione;
import com.procol.procolombia.postulacion.entities.Postulacione;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "vacantes")
public class Vacante {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vacantes_id_gen")
    @SequenceGenerator(name = "vacantes_id_gen", sequenceName = "vacantes_id_vacante_seq", allocationSize = 1)
    @Column(name = "id_vacante", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_ubicacion", nullable = false)
    private Ubicacione idUbicacion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private RelUsuariosEmpresa relUsuariosEmpresas;

    @Size(max = 300)
    @NotNull
    @Column(name = "titulo_vacante", nullable = false, length = 300)
    private String tituloVacante;

    @NotNull
    @Column(name = "detalle_vacante", nullable = false, length = Integer.MAX_VALUE)
    private String detalleVacante;

    @NotNull
    @Column(name = "fecha_inicio_vacante", nullable = false)
    private Instant fechaInicioVacante;

    @NotNull
    @Column(name = "fecha_fin_vacante", nullable = false)
    private Instant fechaFinVacante;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "estado_vacante", nullable = false)
    private Short estadoVacante;

    @OneToOne(mappedBy = "idVacante")
    private Anuncio anuncio;

    @OneToMany(mappedBy = "idVacante")
    private Set<HistorialEstadosVacante> historialEstadosVacantes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idVacante")
    private Set<Postulacione> postulaciones = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "vacantes")
    private Set<PalabrasClave> palabrasClaves = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idVacante")
    private Set<Requisito> requisitos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ubicacione getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Ubicacione idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public RelUsuariosEmpresa getRelUsuariosEmpresas() {
        return relUsuariosEmpresas;
    }

    public void setRelUsuariosEmpresas(RelUsuariosEmpresa relUsuariosEmpresas) {
        this.relUsuariosEmpresas = relUsuariosEmpresas;
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public String getDetalleVacante() {
        return detalleVacante;
    }

    public void setDetalleVacante(String detalleVacante) {
        this.detalleVacante = detalleVacante;
    }

    public Instant getFechaInicioVacante() {
        return fechaInicioVacante;
    }

    public void setFechaInicioVacante(Instant fechaInicioVacante) {
        this.fechaInicioVacante = fechaInicioVacante;
    }

    public Instant getFechaFinVacante() {
        return fechaFinVacante;
    }

    public void setFechaFinVacante(Instant fechaFinVacante) {
        this.fechaFinVacante = fechaFinVacante;
    }

    public Short getEstadoVacante() {
        return estadoVacante;
    }

    public void setEstadoVacante(Short estadoVacante) {
        this.estadoVacante = estadoVacante;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public Set<HistorialEstadosVacante> getHistorialEstadosVacantes() {
        return historialEstadosVacantes;
    }

    public void setHistorialEstadosVacantes(Set<HistorialEstadosVacante> historialEstadosVacantes) {
        this.historialEstadosVacantes = historialEstadosVacantes;
    }

    public Set<Postulacione> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(Set<Postulacione> postulaciones) {
        this.postulaciones = postulaciones;
    }

    public Set<PalabrasClave> getPalabrasClaves() {
        return palabrasClaves;
    }

    public void setPalabrasClaves(Set<PalabrasClave> palabrasClaves) {
        this.palabrasClaves = palabrasClaves;
    }

    public Set<Requisito> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(Set<Requisito> requisitos) {
        this.requisitos = requisitos;
    }

}