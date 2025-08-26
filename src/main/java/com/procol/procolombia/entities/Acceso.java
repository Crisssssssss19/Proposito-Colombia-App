package com.procol.procolombia.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "accesos")
public class Acceso {

    @Id
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "telefono_acceso", nullable = false, length = 150,unique = true)
    private String telefonoAcceso;

    @Column(name = "correo_acceso", nullable = false, length = 150, unique = true)
    private String correoAcceso;

    @Column(name = "clave_acceso", nullable = false, length = 150)
    private String claveAcceso;

    @Column(name = "uuid_acceso", nullable = false, length = 150)
    private String uuidAcceso;

    // Relaciones
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Acceso() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}