package com.procol.procolombia.vacante.entities;

import com.procol.procolombia.auth.entities.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "palabras_claves")
public class PalabraClave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_palabra_clave", nullable = false)
    private Integer id;

    @Size(max = 150)
    @NotNull
    @Column(name = "texto_palabra_clave", nullable = false, length = 150)
    private String textoPalabraClave;

    @ManyToMany
    @JoinTable(name = "rel_usuario_palabra_clave",
            joinColumns = @JoinColumn(name = "id_palabra_Clave"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario"))
    private Set<Usuario> usuarios = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "palabrasClaves")
    private Set<Vacante> vacantes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextoPalabraClave() {
        return textoPalabraClave;
    }

    public void setTextoPalabraClave(String textoPalabraClave) {
        this.textoPalabraClave = textoPalabraClave;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(Set<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

    public PalabraClave() {
    }

    public PalabraClave(Integer id, String textoPalabraClave, Set<Usuario> usuarios, Set<Vacante> vacantes) {
        this.id = id;
        this.textoPalabraClave = textoPalabraClave;
        this.usuarios = usuarios;
        this.vacantes = vacantes;
    }
}