package com.procol.procolombia.entities;

import com.procol.procolombia.entities.idCompuestas.UsuarioRolId;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios_roles")
@IdClass(UsuarioRolId.class)
public class UsuarioRol {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    public UsuarioRol(){}

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}

