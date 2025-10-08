package com.procol.procolombia.perfil.entities;

import com.procol.procolombia.auth.entities.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "talentos")
public class Talento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 100)
    @NotBlank
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre; // Puede ser tanto habilidad como competencia

    @NotNull
    @Min(1)
    @Max(2)
    @Column(name = "tipo", nullable = false)
    private Short tipo; // habilidad = 1, competencia = 2

    @ManyToMany(mappedBy = "talentos", fetch = FetchType.LAZY)
    private List<Usuario> usuarios = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getTipo() {
        return tipo;
    }

    public void setTipo(Short tipo) {
        this.tipo = tipo;
    }
}
