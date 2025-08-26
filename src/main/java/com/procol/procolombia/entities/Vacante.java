package com.procol.procolombia.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vacantes")
public class Vacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacante")
    private Integer idVacante;

    @Column(name = "titulo_vacante", nullable = false, length = 300)
    private String tituloVacante;

    @Column(name = "detalle_vacante", nullable = false, columnDefinition = "TEXT")
    private String detalleVacante;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_inicio_vacante", nullable = false)
    private Date fechaInicioVacante;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_fin_vacante", nullable = false)
    private Date fechaFinVacante;

    @Column(name = "estado_vacante", nullable = false)
    private Short estadoVacante = 1;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion", nullable = false)
    private Ubicacion ubicacion;

    // ✅ Mapeo directo a Empresa
    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    // ✅ Mapeo directo a Usuario (el creador de la vacante)
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "vacante")
    private List<Requisito> requisitos;

    @OneToMany(mappedBy = "vacante")
    private List<Postulacion> postulaciones;

    @OneToMany(mappedBy = "vacante")
    private List<HistorialEstadoVacante> historial;

    @OneToOne(mappedBy = "vacante")
    private Anuncio anuncio;

    @ManyToMany
    @JoinTable(
            name = "rel_vacante_palabraclave",
            joinColumns = @JoinColumn(name = "id_vacante"),
            inverseJoinColumns = @JoinColumn(name = "id_palabra_clave")
    )
    private List<PalabraClave> palabrasClave;

    public Vacante(){}

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
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

    public Date getFechaInicioVacante() {
        return fechaInicioVacante;
    }

    public void setFechaInicioVacante(Date fechaInicioVacante) {
        this.fechaInicioVacante = fechaInicioVacante;
    }

    public Date getFechaFinVacante() {
        return fechaFinVacante;
    }

    public void setFechaFinVacante(Date fechaFinVacante) {
        this.fechaFinVacante = fechaFinVacante;
    }

    public Short getEstadoVacante() {
        return estadoVacante;
    }

    public void setEstadoVacante(Short estadoVacante) {
        this.estadoVacante = estadoVacante;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Requisito> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(List<Requisito> requisitos) {
        this.requisitos = requisitos;
    }

    public List<Postulacion> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(List<Postulacion> postulaciones) {
        this.postulaciones = postulaciones;
    }

    public List<HistorialEstadoVacante> getHistorial() {
        return historial;
    }

    public void setHistorial(List<HistorialEstadoVacante> historial) {
        this.historial = historial;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public List<PalabraClave> getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(List<PalabraClave> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }
}
