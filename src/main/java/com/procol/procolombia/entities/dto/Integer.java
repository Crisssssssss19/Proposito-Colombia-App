package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Empresa}
 */
public class Integer implements Serializable {
    private final java.lang.Integer idEmpresa;
    private final String nombreEmpresa;
    private final String direccionEmpresa;
    private final String telefonoEmpresa;
    private final java.lang.Integer tipoEmpresaIdTipoEmpresa;
    private final List<java.lang.Integer> vacanteIdVacantes;
    private final List<java.lang.Integer> relacionesUsuarios;

    public Integer(java.lang.Integer idEmpresa, String nombreEmpresa, String direccionEmpresa, String telefonoEmpresa, java.lang.Integer tipoEmpresaIdTipoEmpresa, List<java.lang.Integer> vacanteIdVacantes, List<java.lang.Integer> relacionesUsuarios) {
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.direccionEmpresa = direccionEmpresa;
        this.telefonoEmpresa = telefonoEmpresa;
        this.tipoEmpresaIdTipoEmpresa = tipoEmpresaIdTipoEmpresa;
        this.vacanteIdVacantes = vacanteIdVacantes;
        this.relacionesUsuarios = relacionesUsuarios;
    }

    public java.lang.Integer getIdEmpresa() {
        return idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public java.lang.Integer getTipoEmpresaIdTipoEmpresa() {
        return tipoEmpresaIdTipoEmpresa;
    }

    public List<java.lang.Integer> getVacanteIdVacantes() {
        return vacanteIdVacantes;
    }

    public List<java.lang.Integer> getRelacionesUsuarios() {
        return relacionesUsuarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Integer entity = (Integer) o;
        return Objects.equals(this.idEmpresa, entity.idEmpresa) &&
                Objects.equals(this.nombreEmpresa, entity.nombreEmpresa) &&
                Objects.equals(this.direccionEmpresa, entity.direccionEmpresa) &&
                Objects.equals(this.telefonoEmpresa, entity.telefonoEmpresa) &&
                Objects.equals(this.tipoEmpresaIdTipoEmpresa, entity.tipoEmpresaIdTipoEmpresa) &&
                Objects.equals(this.vacanteIdVacantes, entity.vacanteIdVacantes) &&
                Objects.equals(this.relacionesUsuarios, entity.relacionesUsuarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpresa, nombreEmpresa, direccionEmpresa, telefonoEmpresa, tipoEmpresaIdTipoEmpresa, vacanteIdVacantes, relacionesUsuarios);
    }
}