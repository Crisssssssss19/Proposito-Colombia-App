package com.procol.procolombia.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "accesos")
public class Acceso {

    @Id
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Size(max = 150)
    @NotNull
    @Column(name = "telefono_acceso", nullable = false, length = 150)
    private String telefonoAcceso;

    @Size(max = 150)
    @NotNull
    @Column(name = "correo_acceso", nullable = false, length = 150)
    private String correoAcceso;

    @Size(max = 150)
    @NotNull
    @Column(name = "clave_acceso", nullable = false, length = 150)
    private String claveAcceso;

    @Size(max = 150)
    @NotNull
    @Column(name = "uuid_acceso", nullable = false, length = 150)
    private String uuidAcceso;

    @OneToMany(mappedBy = "idUsuario")
    private Set<Ingreso> ingresos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuarios) {
        this.usuario = usuarios;
    }

    public String getTelefonoAcceso() {
        return telefonoAcceso;
    }

    public void setTelefonoAcceso(String telefonoAcceso) {
        this.telefonoAcceso = telefonoAcceso;
    }

    public String getCorreoAcceso() {
        return correoAcceso;
    }

    public void setCorreoAcceso(String correoAcceso) {
        this.correoAcceso = correoAcceso;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public String getUuidAcceso() {
        return uuidAcceso;
    }

    public void setUuidAcceso(String uuidAcceso) {
        this.uuidAcceso = uuidAcceso;
    }

    public Set<Ingreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(Set<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

}