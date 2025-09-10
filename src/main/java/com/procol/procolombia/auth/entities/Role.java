package com.procol.procolombia.auth.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_gen")
    @SequenceGenerator(name = "roles_id_gen", sequenceName = "roles_id_rol_seq", allocationSize = 1)
    @Column(name = "id_rol", nullable = false)
    private Integer idRol;

    @Size(max = 150)
    @NotNull
    @Column(name = "nombre_rol", nullable = false, length = 150)
    private String nombreRol;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "estado_rol", nullable = false)
    private Short estadoRol;

    @ManyToMany
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "id_rol"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario"))
    private Set<Usuario> usuarios = new LinkedHashSet<>();

    public Integer getId() {
        return idRol;
    }

    public void setId(Integer id) {
        this.idRol = id;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Short getEstadoRol() {
        return estadoRol;
    }

    public void setEstadoRol(Short estadoRol) {
        this.estadoRol = estadoRol;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}