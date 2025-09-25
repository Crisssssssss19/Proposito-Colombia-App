package com.procol.procolombia.vacante.entities;

import com.procol.procolombia.auth.entities.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @NotNull
    @Column(name = "tipo_interes", nullable = false)
    private Short tipoInteres;
    @OneToMany(mappedBy = "interes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistorialInteres> historial = new ArrayList<>();


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

    public Interes(InteresId id, List<HistorialInteres> historial, Short tipoInteres, Usuario idUsuario, Empresa idEmpresa) {
        this.id = id;
        this.historial = historial;
        this.tipoInteres = tipoInteres;
        this.idUsuario = idUsuario;
        this.idEmpresa = idEmpresa;
    }
}