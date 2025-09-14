package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tipos_de_empresas")
public class TipoEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_empresa", nullable = false)
    private Integer id;

    @Size(max = 150)
    @NotNull
    @Column(name = "nombre_tipo_empresa", nullable = false, length = 150)
    private String nombreTipoEmpresa;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "estado_tipo_empresa", nullable = false)
    private Short estadoTipoEmpresa;

    @OneToMany(mappedBy = "idTipoEmpresa")
    private Set<Empresa> empresas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(Set<Empresa> empresas) {
        this.empresas = empresas;
    }

    public TipoEmpresa() {
    }

    public TipoEmpresa(Integer id, String nombreTipoEmpresa, Short estadoTipoEmpresa, Set<Empresa> empresas) {
        this.id = id;
        this.nombreTipoEmpresa = nombreTipoEmpresa;
        this.estadoTipoEmpresa = estadoTipoEmpresa;
        this.empresas = empresas;
    }
}