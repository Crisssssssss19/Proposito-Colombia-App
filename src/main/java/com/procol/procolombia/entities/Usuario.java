package com.procol.procolombia.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "tipo_documento_usuario", nullable = false)
    private Short tipoDocumentoUsuario;

    @Column(name = "documento_usuario", nullable = false, unique = true, length = 150)
    private String documentoUsuario;

    @Column(name = "nombres_usuario", nullable = false, length = 50)
    private String nombresUsuario;

    @Column(name = "apellidos_usuario", nullable = false, length = 50)
    private String apellidosUsuario;

    @Column(name = "estado_usuario", nullable = false)
    private Short estadoUsuario = 1;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion", nullable = false)
    private Ubicacion ubicacion;

    @OneToOne(mappedBy = "usuario")
    private Acceso acceso;

    @OneToMany(mappedBy = "usuario")
    private List<Archivo> archivos;

    @OneToMany(mappedBy = "usuario")
    private List<Imagen> imagenes;

    @OneToMany(mappedBy = "usuario")
    private List<Auditoria> auditorias;

    @OneToMany(mappedBy = "usuario")
    private List<Postulacion> postulaciones;

    @OneToMany(mappedBy = "usuarioResponde")
    private List<Mensaje> mensajes;

    @OneToMany(mappedBy = "usuario")
    private List<Interes> intereses;

    @OneToMany(mappedBy = "usuario")
    private List<RelUsuarioEmpresa> relacionesEmpresas;

    @ManyToMany
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private List<Rol> roles;

    @ManyToMany
    @JoinTable(
            name = "rel_usuario_palabraclave",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_palabra_clave")
    )
    private List<PalabraClave> palabrasClave;

    public Usuario(){}

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Acceso getAcceso() {
        return acceso;
    }

    public void setAcceso(Acceso acceso) {
        this.acceso = acceso;
    }

    public List<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<Archivo> archivos) {
        this.archivos = archivos;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public List<Auditoria> getAuditorias() {
        return auditorias;
    }

    public void setAuditorias(List<Auditoria> auditorias) {
        this.auditorias = auditorias;
    }

    public List<Postulacion> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(List<Postulacion> postulaciones) {
        this.postulaciones = postulaciones;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public List<Interes> getIntereses() {
        return intereses;
    }

    public void setIntereses(List<Interes> intereses) {
        this.intereses = intereses;
    }

    public List<RelUsuarioEmpresa> getRelacionesEmpresas() {
        return relacionesEmpresas;
    }

    public void setRelacionesEmpresas(List<RelUsuarioEmpresa> relacionesEmpresas) {
        this.relacionesEmpresas = relacionesEmpresas;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public List<PalabraClave> getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(List<PalabraClave> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }
}
