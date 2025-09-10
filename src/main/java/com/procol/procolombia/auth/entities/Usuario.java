package com.procol.procolombia.auth.entities;

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
    private Set<Interes> interes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUsuarioResponde")
    private Set<Mensaje> mensajes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUsuario")
    private Set<Postulacione> postulaciones = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "usuarios")
    private Set<PalabraClave> palabraClaves = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUsuario")
    private Set<RelUsuarioEmpresa> relUsuarioEmpresas = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "usuarios")
    private Set<Role> roles = new LinkedHashSet<>();

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
        return interes;
    }

    public void setIntereses(Set<Interes> interes) {
        this.interes = interes;
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
        return palabraClaves;
    }

    public void setPalabrasClaves(Set<PalabraClave> palabraClaves) {
        this.palabraClaves = palabraClaves;
    }

    public Set<RelUsuarioEmpresa> getRelUsuariosEmpresas() {
        return relUsuarioEmpresas;
    }

    public void setRelUsuariosEmpresas(Set<RelUsuarioEmpresa> relUsuarioEmpresas) {
        this.relUsuarioEmpresas = relUsuarioEmpresas;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}