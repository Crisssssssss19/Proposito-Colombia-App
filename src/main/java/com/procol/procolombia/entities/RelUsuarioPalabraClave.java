package com.procol.procolombia.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rel_usuario_palabraclave")
@IdClass(RelUsuarioPalabraClaveId.class)
public class RelUsuarioPalabraClave {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_palabra_clave", nullable = false)
    private PalabraClave palabraClave;

    public RelUsuarioPalabraClave(){}

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PalabraClave getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(PalabraClave palabraClave) {
        this.palabraClave = palabraClave;
    }
}

class RelUsuarioPalabraClaveId implements java.io.Serializable {
    private Integer usuario;
    private Integer palabraClave;
}