package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tipo_empresas")
public class TipoEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_empresas_id_gen")
    @SequenceGenerator(name = "tipo_empresas_id_gen", sequenceName = "tipo_empresas_id_tipo_empresa_seq", allocationSize = 1)
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

}