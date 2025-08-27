package com.procol.procolombia.entities.idCompuestas;
import java.util.Objects;

public class RelUsuarioEmpresaId implements java.io.Serializable {
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