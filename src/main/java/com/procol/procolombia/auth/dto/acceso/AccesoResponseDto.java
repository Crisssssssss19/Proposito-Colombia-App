package com.procol.procolombia.auth.dto.acceso;

import com.procol.procolombia.auth.entities.Acceso;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Acceso}
 */
public class AccesoResponseDto implements Serializable {
    private final Integer idUsuario;
    private final String telefonoAcceso;
    private final String correoAcceso;
    private final String claveAcceso;
    private final String uuidAcceso;
    private final Integer usuarioIdUsuario;

    public AccesoResponseDto(Integer idUsuario, String telefonoAcceso, String correoAcceso, String claveAcceso, String uuidAcceso, Integer usuarioIdUsuario) {
        this.idUsuario = idUsuario;
        this.telefonoAcceso = telefonoAcceso;
        this.correoAcceso = correoAcceso;
        this.claveAcceso = claveAcceso;
        this.uuidAcceso = uuidAcceso;
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getTelefonoAcceso() {
        return telefonoAcceso;
    }

    public String getCorreoAcceso() {
        return correoAcceso;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public String getUuidAcceso() {
        return uuidAcceso;
    }

    public Integer getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccesoResponseDto entity = (AccesoResponseDto) o;
        return Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.telefonoAcceso, entity.telefonoAcceso) &&
                Objects.equals(this.correoAcceso, entity.correoAcceso) &&
                Objects.equals(this.claveAcceso, entity.claveAcceso) &&
                Objects.equals(this.uuidAcceso, entity.uuidAcceso) &&
                Objects.equals(this.usuarioIdUsuario, entity.usuarioIdUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, telefonoAcceso, correoAcceso, claveAcceso, uuidAcceso, usuarioIdUsuario);
    }
}