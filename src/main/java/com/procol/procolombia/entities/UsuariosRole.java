package com.procol.procolombia.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "usuarios_roles")
public class UsuariosRole {
    @SequenceGenerator(name = "usuarios_roles_id_gen", sequenceName = "vacantes_id_vacante_seq", allocationSize = 1)
    @EmbeddedId
    private UsuariosRoleId id;

    @MapsId("idRol")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_rol", nullable = false)
    private Role idRol;

    @MapsId("idUsuario")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    public UsuariosRoleId getId() {
        return id;
    }

    public void setId(UsuariosRoleId id) {
        this.id = id;
    }

    public Role getIdRol() {
        return idRol;
    }

    public void setIdRol(Role idRol) {
        this.idRol = idRol;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

}