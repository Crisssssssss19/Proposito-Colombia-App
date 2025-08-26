package com.procol.procolombia.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

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

class RelUsuarioEmpresaId implements java.io.Serializable {
    private Integer usuario;
    private Integer empresa;

    public RelUsuarioEmpresaId() {
    }

    public RelUsuarioEmpresaId(Integer usuario, Integer empresa) {
        this.usuario = usuario;
        this.empresa = empresa;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Integer empresa) {
        this.empresa = empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelUsuarioEmpresaId that = (RelUsuarioEmpresaId) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(empresa, that.empresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, empresa);
    }
}