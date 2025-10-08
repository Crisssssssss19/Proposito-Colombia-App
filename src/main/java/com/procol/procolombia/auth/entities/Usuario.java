package com.procol.procolombia.auth.entities;

import com.procol.procolombia.perfil.entities.Talento;
import com.procol.procolombia.postulacion.entities.Archivo;
import com.procol.procolombia.postulacion.entities.Mensaje;
import com.procol.procolombia.postulacion.entities.Postulacione;
import com.procol.procolombia.vacante.entities.Interes;
import com.procol.procolombia.vacante.entities.PalabraClave;
import com.procol.procolombia.vacante.entities.RelUsuarioEmpresa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_id_gen")
    @SequenceGenerator(name = "usuarios_id_gen", sequenceName = "usuarios_id_usuario_seq", allocationSize = 1)
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_ubicacion", nullable = false)
    private Ubicacione idUbicacion;

    @NotNull
    @Column(name = "tipo_documento_usuario", nullable = false)
    private Short tipoDocumentoUsuario;

    @Size(max = 150)
    @NotNull
    @Column(name = "documento_usuario", nullable = false, length = 150)
    private String documentoUsuario;

    @Size(max = 50)
    @NotNull
    @Column(name = "nombres_usuario", nullable = false, length = 50)
    private String nombresUsuario;

    @Size(max = 50)
    @NotNull
    @Column(name = "apellidos_usuario", nullable = false, length = 50)
    private String apellidosUsuario;

    @NotNull
    @Column(name = "estado_usuario", nullable = false)
    private Short estadoUsuario;

    @OneToOne(mappedBy = "usuario")
    private Acceso acceso;

    @OneToMany(mappedBy = "idUsuario")
    private Set<Archivo> archivos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUsuario")
    private Set<Imagene> imagenes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUsuario")
    private Set<Interes> intereses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUsuarioResponde")
    private Set<Mensaje> mensajes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUsuario")
    private Set<Postulacione> postulaciones = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "rel_usuario_palabraclave",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_palabra_clave")
    )
    private Set<PalabraClave> palabrasClaves = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUsuario")
    private Set<RelUsuarioEmpresa> relUsuarioEmpresas = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "usuarios")
    private Set<Role> roles = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "usuario_talentos",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_talento")
    )
    private Set<Talento> talentos = new LinkedHashSet<>();

    public Usuario(Integer id, Short tipoDocumentoUsuario, String documentoUsuario, String apellidosUsuario, String nombresUsuario, Short estadoUsuario, Acceso acceso, Set<Archivo> archivos, Set<Imagene> imagenes, Set<Interes> interes, Set<Mensaje> mensajes, Ubicacione idUbicacion, Set<Postulacione> postulaciones, Set<PalabraClave> palabraClaves, Set<RelUsuarioEmpresa> relUsuarioEmpresas, Set<Role> roles) {
        this.id = id;
        this.tipoDocumentoUsuario = tipoDocumentoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.nombresUsuario = nombresUsuario;
        this.estadoUsuario = estadoUsuario;
        this.acceso = acceso;
        this.archivos = archivos;
        this.imagenes = imagenes;
        this.intereses = interes;
        this.mensajes = mensajes;
        this.idUbicacion = idUbicacion;
        this.postulaciones = postulaciones;
        this.palabrasClaves = palabraClaves;
        this.relUsuarioEmpresas = relUsuarioEmpresas;
        this.roles = roles;
    }

    public Usuario(Integer id, String s) {
    }

    public Usuario() {

    }

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

    public Short getTipoDocumentoUsuario() {
        return tipoDocumentoUsuario;
    }

    public void setTipoDocumentoUsuario(Short tipoDocumentoUsuario) {
        this.tipoDocumentoUsuario = tipoDocumentoUsuario;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getNombresUsuario() {
        return nombresUsuario;
    }

    public void setNombresUsuario(String nombresUsuario) {
        this.nombresUsuario = nombresUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public Short getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(Short estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public Acceso getAcceso() {
        return acceso;
    }

    public void setAcceso(Acceso acceso) {
        this.acceso = acceso;
    }

    public Set<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(Set<Archivo> archivos) {
        this.archivos = archivos;
    }

    public Set<Imagene> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<Imagene> imagenes) {
        this.imagenes = imagenes;
    }

    public Set<Interes> getIntereses() {
        return intereses;
    }

    public void setIntereses(Set<Interes> interes) {
        this.intereses = interes;
    }

    public Set<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(Set<Mensaje> mensajes) {
        this.mensajes = mensajes;
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

    public void setPalabrasClaves(Set<PalabraClave> palabraClaves) {
        this.palabrasClaves = palabraClaves;
    }

    public Set<RelUsuarioEmpresa> getRelUsuarioEmpresas() {
        return relUsuarioEmpresas;
    }

    public void setRelUsuarioEmpresas(Set<RelUsuarioEmpresa> relUsuarioEmpresas) {
        this.relUsuarioEmpresas = relUsuarioEmpresas;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Talento> getTalentos() {
        return talentos;
    }

    public void setTalentos(Set<Talento> talentos) {
        this.talentos = talentos;
    }
}