package com.procol.procolombia.vacante.entities;

import com.procol.procolombia.auth.entities.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "relUsuariosEmpresas")
public class RelUsuarioEmpresa {

    @EmbeddedId
    private RelUsuarioEmpresaId id;

    @MapsId("idUsuario")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @MapsId("idEmpresa")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa idEmpresa;

    @NotNull
    @ColumnDefault("2")
    @Column(name = "permisoRelUsuarioEmpresa", nullable = false)
    private Short permisoRelUsuarioEmpresa;

    @OneToMany(mappedBy = "relUsuariosEmpresas")
    private Set<Vacante> vacantes = new LinkedHashSet<>();

    public RelUsuarioEmpresaId getId() {
        return id;
    }

    public void setId(RelUsuarioEmpresaId id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Short getPermisoRelUsuarioEmpresa() {
        return permisoRelUsuarioEmpresa;
    }

    public void setPermisoRelUsuarioEmpresa(Short permisoRelUsuarioEmpresa) {
        this.permisoRelUsuarioEmpresa = permisoRelUsuarioEmpresa;
    }

    public Set<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(Set<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

    public RelUsuarioEmpresa() {
    }

    public RelUsuarioEmpresa(RelUsuarioEmpresaId id, Usuario idUsuario, Empresa idEmpresa, Short permisoRelUsuarioEmpresa, Set<Vacante> vacantes) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idEmpresa = idEmpresa;
        this.permisoRelUsuarioEmpresa = permisoRelUsuarioEmpresa;
        this.vacantes = vacantes;
    }
}