package com.procol.procolombia.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Integer idEmpresa;

    @Column(name = "nombre_empresa", nullable = false, length = 200)
    private String nombreEmpresa;

    @Column(name = "direccion_empresa", nullable = false, length = 200)
    private String direccionEmpresa;

    @Column(name = "telefono_empresa", nullable = false, length = 200)
    private String telefonoEmpresa;

    @ManyToOne
    @JoinColumn(name = "id_tipo_empresa", nullable = false)
    private TipoEmpresa tipoEmpresa;

    @OneToMany(mappedBy = "empresa")
    private List<Vacante> vacantes;

    @OneToMany(mappedBy = "empresa")
    private List<Interes> intereses;

    @OneToMany(mappedBy = "empresa")
    private List<RelUsuarioEmpresa> relacionesUsuarios;

    public Empresa(){}

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
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

    public TipoEmpresa getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public List<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(List<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

    public List<Interes> getIntereses() {
        return intereses;
    }

    public void setIntereses(List<Interes> intereses) {
        this.intereses = intereses;
    }

    public List<RelUsuarioEmpresa> getRelacionesUsuarios() {
        return relacionesUsuarios;
    }

    public void setRelacionesUsuarios(List<RelUsuarioEmpresa> relacionesUsuarios) {
        this.relacionesUsuarios = relacionesUsuarios;
    }
}
