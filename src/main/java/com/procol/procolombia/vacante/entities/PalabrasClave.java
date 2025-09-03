package com.procol.procolombia.vacante.entities;

import com.procol.procolombia.auth.entities.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "palabras_claves")
public class PalabrasClave {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "palabras_claves_id_gen")
    @SequenceGenerator(name = "palabras_claves_id_gen", sequenceName = "palabras_claves_id_palabra_clave_seq", allocationSize = 1)
    @Column(name = "id_palabra_clave", nullable = false)
    private Integer id;

    @Size(max = 150)
    @NotNull
    @Column(name = "texto_palabra_clave", nullable = false, length = 150)
    private String textoPalabraClave;

    @ManyToMany
    @JoinTable(name = "rel_usuario_palabraclave",
            joinColumns = @JoinColumn(name = "id_palabra_clave"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario"))
    private Set<Usuario> usuarios = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "rel_vacante_palabraclave",
            joinColumns = @JoinColumn(name = "id_palabra_clave"),
            inverseJoinColumns = @JoinColumn(name = "id_vacante"))
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

}