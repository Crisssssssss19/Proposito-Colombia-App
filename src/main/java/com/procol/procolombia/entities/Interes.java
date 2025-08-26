package com.procol.procolombia.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "intereses")
@IdClass(InteresId.class)
public class Interes {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "tipo_interes", nullable = false)
    private Short tipoInteres;

    public Interes(){}

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Short getTipoInteres() {
        return tipoInteres;
    }

    public void setTipoInteres(Short tipoInteres) {
        this.tipoInteres = tipoInteres;
    }
}

class InteresId implements java.io.Serializable {
    private Integer empresa;
    private Integer usuario;
}