package com.procol.procolombia.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "palabras_claves")
public class PalabraClave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_palabra_clave")
    private Integer idPalabraClave;

    @Column(name = "texto_palabra_clave", nullable = false, length = 150, unique = true)
    private String textoPalabraClave;

    @ManyToMany(mappedBy = "palabrasClave")
    private List<Usuario> usuarios;

    @ManyToMany(mappedBy = "palabrasClave")
    private List<Vacante> vacantes;

    public PalabraClave(){}

    public Integer getIdPalabraClave() {
        return idPalabraClave;
    }

    public void setIdPalabraClave(Integer idPalabraClave) {
        this.idPalabraClave = idPalabraClave;
    }

    public String getTextoPalabraClave() {
        return textoPalabraClave;
    }

    public void setTextoPalabraClave(String textoPalabraClave) {
        this.textoPalabraClave = textoPalabraClave;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(List<Vacante> vacantes) {
        this.vacantes = vacantes;
    }
}
