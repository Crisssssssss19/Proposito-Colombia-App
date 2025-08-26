package com.procol.procolombia.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "auditorias")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Integer idAuditoria;

    @Column(name = "nombre_entidad_auditoria", nullable = false, length = 100)
    private String nombreEntidadAuditoria;

    @Column(name = "id_referencia_auditoria", nullable = false)
    private Integer idReferenciaAuditoria;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_auditoria", nullable = false)
    private Date fechaAuditoria;

    @Column(name = "tipo_cambio_auditoria", nullable = false, length = 200)
    private String tipoCambioAuditoria;

    @Column(name = "comentario_auditoria", nullable = false, columnDefinition = "TEXT")
    private String comentarioAuditoria;

    @ManyToOne
    @JoinColumn(name = "id_usuario_auditoria", nullable = false)
    private Usuario usuario;

    public Auditoria(){}

    public Integer getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public String getNombreEntidadAuditoria() {
        return nombreEntidadAuditoria;
    }

    public void setNombreEntidadAuditoria(String nombreEntidadAuditoria) {
        this.nombreEntidadAuditoria = nombreEntidadAuditoria;
    }

    public Integer getIdReferenciaAuditoria() {
        return idReferenciaAuditoria;
    }

    public void setIdReferenciaAuditoria(Integer idReferenciaAuditoria) {
        this.idReferenciaAuditoria = idReferenciaAuditoria;
    }

    public Date getFechaAuditoria() {
        return fechaAuditoria;
    }

    public void setFechaAuditoria(Date fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }

    public String getTipoCambioAuditoria() {
        return tipoCambioAuditoria;
    }

    public void setTipoCambioAuditoria(String tipoCambioAuditoria) {
        this.tipoCambioAuditoria = tipoCambioAuditoria;
    }

    public String getComentarioAuditoria() {
        return comentarioAuditoria;
    }

    public void setComentarioAuditoria(String comentarioAuditoria) {
        this.comentarioAuditoria = comentarioAuditoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}