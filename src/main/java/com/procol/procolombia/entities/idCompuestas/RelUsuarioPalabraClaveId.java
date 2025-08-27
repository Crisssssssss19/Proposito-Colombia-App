package com.procol.procolombia.entities.idCompuestas;

import java.util.Objects;

public class RelUsuarioPalabraClaveId implements java.io.Serializable {
    private Integer usuario;
    private Integer palabraClave;

    public RelUsuarioPalabraClaveId() {
    }

    public RelUsuarioPalabraClaveId(Integer usuario, Integer palabraClave) {
        this.usuario = usuario;
        this.palabraClave = palabraClave;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(Integer palabraClave) {
        this.palabraClave = palabraClave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelUsuarioPalabraClaveId that = (RelUsuarioPalabraClaveId) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(palabraClave, that.palabraClave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, palabraClave);
    }
}