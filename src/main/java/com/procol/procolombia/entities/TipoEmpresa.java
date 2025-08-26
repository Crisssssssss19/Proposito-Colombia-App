package com.procol.procolombia.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tipo_empresas")
public class TipoEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_empresa")
    private Integer idTipoEmpresa;

    @Column(name = "nombre_tipo_empresa", nullable = false, length = 150, unique = true)
    private String nombreTipoEmpresa;

    @Column(name = "estado_tipo_empresa", nullable = false)
    private Short estadoTipoEmpresa;

    @OneToMany(mappedBy = "tipoEmpresa")
    private List<Empresa> empresas;

    public TipoEmpresa(){}

    public Integer getIdTipoEmpresa() {
        return idTipoEmpresa;
    }

    public void setIdTipoEmpresa(Integer idTipoEmpresa) {
        this.idTipoEmpresa = idTipoEmpresa;
    }

    public String getNombreTipoEmpresa() {
        return nombreTipoEmpresa;
    }

    public void setNombreTipoEmpresa(String nombreTipoEmpresa) {
        this.nombreTipoEmpresa = nombreTipoEmpresa;
    }

    public Short getEstadoTipoEmpresa() {
        return estadoTipoEmpresa;
    }

    public void setEstadoTipoEmpresa(Short estadoTipoEmpresa) {
        this.estadoTipoEmpresa = estadoTipoEmpresa;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
}