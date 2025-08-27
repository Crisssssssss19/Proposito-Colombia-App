package com.procol.procolombia.entities;

import com.procol.procolombia.entities.idCompuestas.RelUsuarioEmpresaId;
import jakarta.persistence.*;

@Entity
@Table(name = "rel_usuarios_empresas")
@IdClass(RelUsuarioEmpresaId.class)
public class RelUsuarioEmpresa {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    @Column(name = "permiso_rel_usuario_empresa", nullable = false)
    private Short permisoRelUsuarioEmpresa = 2;

    public RelUsuarioEmpresa(){}

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Short getPermisoRelUsuarioEmpresa() {
        return permisoRelUsuarioEmpresa;
    }

    public void setPermisoRelUsuarioEmpresa(Short permisoRelUsuarioEmpresa) {
        this.permisoRelUsuarioEmpresa = permisoRelUsuarioEmpresa;
    }
}

