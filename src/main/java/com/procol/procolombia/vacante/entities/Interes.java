package com.procol.procolombia.vacante.entities;

import com.procol.procolombia.auth.entities.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "intereses")
public class Interes {

    @EmbeddedId
    private InteresId id;

    @MapsId("idEmpresa")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa idEmpresa;

    @MapsId("idUsuario")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario idUsuario;

    @NotNull
    @Column(name = "tipoInteres", nullable = false)
    private Short tipoInteres;

    public InteresId getId() {
        return id;
    }

    public void setId(InteresId id) {
        this.id = id;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Short getTipoInteres() {
        return tipoInteres;
    }

    public void setTipoInteres(Short tipoInteres) {
        this.tipoInteres = tipoInteres;
    }

    public Interes() {
    }

    public Interes(InteresId id, Empresa idEmpresa, Usuario idUsuario, Short tipoInteres) {
        this.id = id;
        this.idEmpresa = idEmpresa;
        this.idUsuario = idUsuario;
        this.tipoInteres = tipoInteres;
    }
}