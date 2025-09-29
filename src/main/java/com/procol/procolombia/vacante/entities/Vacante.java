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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private RelUsuarioEmpresa relUsuarioEmpresas;


    @Size(max = 300)
    @NotNull
    @Column(name = "titulo_vacante", nullable = false, length = 300)
    private String tituloVacante;

    @NotNull
    @Column(name = "descripcion_corta", length = 300)
    private String descripcionCorta;

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
    private Set<HistorialEstadoVacante> historialEstadoVacantes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idVacante")
    private Set<Postulacione> postulaciones = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "rel_vacante_palabra_clave",
            joinColumns = @JoinColumn(name = "id_vacante"),
            inverseJoinColumns = @JoinColumn(name = "id_palabra_clave"))
    private Set<PalabraClave> palabrasClaves = new LinkedHashSet<>();


    @OneToMany(mappedBy = "idVacante")
    private Set<Requisito> requisitos = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rango", nullable = false)
    private RangoSalarial rangoSalarial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_periodicidad", nullable = false)
    private PeriodicidadPago periodicidadPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jornada", nullable = false)
    private Jornada jornada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modalidad", nullable = false)
    private Modalidad modalidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato", nullable = false)
    private Contrato contrato;

    public Vacante() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public RangoSalarial getRangoSalarial() {
        return rangoSalarial;
    }

    public void setRangoSalarial(RangoSalarial rangoSalarial) {
        this.rangoSalarial = rangoSalarial;
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
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

    public Set<HistorialEstadoVacante> getHistorialEstadoVacantes() {
        return historialEstadoVacantes;
    }

    public void setHistorialEstadoVacantes(Set<HistorialEstadoVacante> historialEstadoVacantes) {
        this.historialEstadoVacantes = historialEstadoVacantes;
    }

    public Set<Postulacione> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(Set<Postulacione> postulaciones) {
        this.postulaciones = postulaciones;
    }

    public Set<PalabraClave> getPalabrasClaves() {
        return palabrasClaves;
    }

    public void setPalabrasClaves(Set<PalabraClave> palabrasClaves) {
        this.palabrasClaves = palabrasClaves;
    }

    public Set<Requisito> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(Set<Requisito> requisitos) {
        this.requisitos = requisitos;
    }

    public RelUsuarioEmpresa getRelUsuarioEmpresas() {
        return relUsuarioEmpresas;
    }

    public void setRelUsuarioEmpresas(RelUsuarioEmpresa relUsuarioEmpresas) {
        this.relUsuarioEmpresas = relUsuarioEmpresas;
    }

    public Vacante(Integer id, Ubicacione idUbicacion, RelUsuarioEmpresa relUsuarioEmpresas, String tituloVacante, String descripcionCorta, Instant fechaInicioVacante, Instant fechaFinVacante, Short estadoVacante, Anuncio anuncio, Set<HistorialEstadoVacante> historialEstadoVacantes, Set<Postulacione> postulaciones, Set<PalabraClave> palabrasClaves, Set<Requisito> requisitos, RangoSalarial rangoSalarial, PeriodicidadPago periodicidadPago, Jornada jornada, Modalidad modalidad, Contrato contrato) {
        this.id = id;
        this.idUbicacion = idUbicacion;
        this.relUsuarioEmpresas = relUsuarioEmpresas;
        this.tituloVacante = tituloVacante;
        this.descripcionCorta = descripcionCorta;
        this.fechaInicioVacante = fechaInicioVacante;
        this.fechaFinVacante = fechaFinVacante;
        this.estadoVacante = estadoVacante;
        this.anuncio = anuncio;
        this.historialEstadoVacantes = historialEstadoVacantes;
        this.postulaciones = postulaciones;
        this.palabrasClaves = palabrasClaves;
        this.requisitos = requisitos;
        this.rangoSalarial = rangoSalarial;
        this.periodicidadPago = periodicidadPago;
        this.jornada = jornada;
        this.modalidad = modalidad;
        this.contrato = contrato;
    }

    public Ubicacione getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Ubicacione idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public PeriodicidadPago getPeriodicidadPago() {
        return periodicidadPago;
    }

    public void setPeriodicidadPago(PeriodicidadPago periodicidadPago) {
        this.periodicidadPago = periodicidadPago;
    }
}