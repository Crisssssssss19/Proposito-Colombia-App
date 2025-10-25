package com.procol.procolombia.perfil.entities;

import com.procol.procolombia.auth.entities.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usuario_talento")
public class UsuarioTalento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTalento", nullable = false)
    private Talento talento;

    @NotNull
    @Min(1)
    @Max(3)
    @Column(name = "nivel_dominio", nullable = false)
    private Integer nivelDominio; // 1 = Básico, 2 = Intermedio, 3 = Avanzado

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Talento getTalento() {
        return talento;
    }

    public void setTalento(Talento talento) {
        this.talento = talento;
    }

    public Integer getNivelDominio() {
        return nivelDominio;
    }

    public void setNivelDominio(Integer nivelDominio) {
        this.nivelDominio = nivelDominio;
    }
}
