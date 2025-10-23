package com.procol.procolombia.postulacion.entities;

import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.vacante.entities.PalabraClave;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "rel_usuario_palabraclave")
public class RelUsuarioPalabraclave {
    @SequenceGenerator(name = "rel_usuario_palabraclave_id_gen", sequenceName = "postulaciones_id_postulacion_seq", allocationSize = 1)
    @EmbeddedId
    private RelUsuarioPalabraclaveId id;

    @MapsId("idUsuario")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @MapsId("idPalabraClave")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_palabra_clave", nullable = false)
    private PalabraClave idPalabraClave;

    public RelUsuarioPalabraclaveId getId() {
        return id;
    }

    public void setId(RelUsuarioPalabraclaveId id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public PalabraClave getIdPalabraClave() {
        return idPalabraClave;
    }

    public void setIdPalabraClave(PalabraClave idPalabraClave) {
        this.idPalabraClave = idPalabraClave;
    }

}