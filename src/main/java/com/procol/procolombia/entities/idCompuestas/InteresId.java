package com.procol.procolombia.entities.idCompuestas;

import java.util.Objects;

public class InteresId implements java.io.Serializable {
    private Integer empresa;
    private Integer usuario;

    public InteresId() {
    }

    public InteresId(Integer empresa, Integer usuario) {
        this.empresa = empresa;
        this.usuario = usuario;
    }

    public Integer getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Integer empresa) {
        this.empresa = empresa;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InteresId interesId = (InteresId) o;
        return Objects.equals(empresa, interesId.empresa) && Objects.equals(usuario, interesId.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empresa, usuario);
    }
}