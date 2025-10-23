package com.procol.procolombia.vacante.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "empresas")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_tipo_empresa", nullable = false)
    private TipoEmpresa idTipoEmpresa;

    @Size(max = 200)
    @NotNull
    @Column(name = "nombre_empresa", nullable = false, length = 200)
    private String nombreEmpresa;

    @Size(max = 200)
    @NotNull
    @Column(name = "direccion_empresa", nullable = false, length = 200)
    private String direccionEmpresa;

    @Size(max = 200)
    @NotNull
    @Column(name = "telefono_empresa", nullable = false, length = 200)
    private String telefonoEmpresa;

    @OneToMany(mappedBy = "idEmpresa")
    private Set<Interes> interes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEmpresa")
    private Set<RelUsuarioEmpresa> relUsuarioEmpresas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoEmpresa getIdTipoEmpresa() {
        return idTipoEmpresa;
    }

    public void setIdTipoEmpresa(TipoEmpresa idTipoEmpresa) {
        this.idTipoEmpresa = idTipoEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public Set<Interes> getIntereses() {
        return interes;
    }

    public void setIntereses(Set<Interes> interes) {
        this.interes = interes;
    }

    public Set<RelUsuarioEmpresa> getRelUsuarioEmpresas() {
        return relUsuarioEmpresas;
    }

    public void setRelUsuarioEmpresas(Set<RelUsuarioEmpresa> relUsuarioEmpresas) {
        this.relUsuarioEmpresas = relUsuarioEmpresas;
    }
    public Empresa() {
    }

    public Empresa(Integer id,
                   TipoEmpresa idTipoEmpresa,
                   String nombreEmpresa,
                   String direccionEmpresa,
                   String telefonoEmpresa,
                   Set<Interes> interes,
                   Set<RelUsuarioEmpresa> relUsuarioEmpresas) {
        this.id = id;
        this.idTipoEmpresa = idTipoEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.direccionEmpresa = direccionEmpresa;
        this.telefonoEmpresa = telefonoEmpresa;
        this.interes = interes;
        this.relUsuarioEmpresas = relUsuarioEmpresas;
    }

}