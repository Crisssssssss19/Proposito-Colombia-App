package com.procol.procolombia.auth.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity(name = "correo_verificacion")
@Table(name = "correo_verificacion")
public class CorreoVerificacion {
    @Id
    @Column(name = "id_correo_verificado", nullable = false)
    private String idCorreo;

    @Column(name = "pin_correo_verificado", nullable = true, length = 20)
    private String pinCorreo;

    @Column(name = "estado_correo_verificado", nullable = false)
    private Short estadoCorreoVerificado; // 1=sin verificar, 2=pendiente, 3=verificado

    public String getIdCorreo() {
        return idCorreo;
    }
    public void setIdCorreo(String idCorreo) {
        this.idCorreo = idCorreo;
    }
    public String getPinCorreo() {
        return pinCorreo;
    }
    public void setPinCorreo(String pinCorreo) {
        this.pinCorreo = pinCorreo;
    }
    public Short getEstadoCorreoVerificado() {
        return estadoCorreoVerificado;
    }
    public void setEstadoCorreoVerificado(Short estadoCorreoVerificado) { this.estadoCorreoVerificado = estadoCorreoVerificado; }
}
