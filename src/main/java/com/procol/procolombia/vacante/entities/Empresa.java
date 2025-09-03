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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresas_id_gen")
    @SequenceGenerator(name = "empresas_id_gen", sequenceName = "empresas_id_empresa_seq", allocationSize = 1)
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
    private Set<Interese> intereses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEmpresa")
    private Set<RelUsuariosEmpresa> relUsuariosEmpresas = new LinkedHashSet<>();

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

    public Set<Interese> getIntereses() {
        return intereses;
    }

    public void setIntereses(Set<Interese> intereses) {
        this.intereses = intereses;
    }

    public Set<RelUsuariosEmpresa> getRelUsuariosEmpresas() {
        return relUsuariosEmpresas;
    }

    public void setRelUsuariosEmpresas(Set<RelUsuariosEmpresa> relUsuariosEmpresas) {
        this.relUsuariosEmpresas = relUsuariosEmpresas;
    }

}