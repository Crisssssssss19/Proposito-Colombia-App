package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Usuario}
 */
public class UsuarioDto implements Serializable {
    private final Integer idUsuario;
    private final Short tipoDocumentoUsuario;
    private final String documentoUsuario;
    private final String nombresUsuario;
    private final String apellidosUsuario;
    private final Short estadoUsuario;
    private final Integer ubicacionIdUbicacion;
    private final Integer accesoIdUsuario;
    private final List<Integer> idArchivos;
    private final List<Integer> idImagenes;
    private final List<Integer> idAuditorias;
    private final List<Integer> idPostulaciones;
    private final List<Integer> mensajeIdMensajes;
    private final List<Integer> idIntereses;
    private final List<Integer> idRelacionesEmpresas;
    private final List<Integer> idRoles;
    private final List<Integer> idPalabraClaves;

    public UsuarioDto(Integer idUsuario, Short tipoDocumentoUsuario, String documentoUsuario, String nombresUsuario, String apellidosUsuario, Short estadoUsuario, Integer ubicacionIdUbicacion, Integer accesoIdUsuario, List<Integer> archivoIdArchivos, List<Integer> imageneIdImagens, List<Integer> auditoriaIdAuditorias, List<Integer> postulacioneIdPostulacions, List<Integer> mensajeIdMensajes, List<Integer> intereses, List<Integer> relacionesEmpresas, List<Integer> roleIdRols, List<Integer> palabrasClaveIdPalabraClaves) {
        this.idUsuario = idUsuario;
        this.tipoDocumentoUsuario = tipoDocumentoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.nombresUsuario = nombresUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.estadoUsuario = estadoUsuario;
        this.ubicacionIdUbicacion = ubicacionIdUbicacion;
        this.accesoIdUsuario = accesoIdUsuario;
        this.idArchivos = archivoIdArchivos;
        this.idImagenes = imageneIdImagens;
        this.idAuditorias = auditoriaIdAuditorias;
        this.idPostulaciones = postulacioneIdPostulacions;
        this.mensajeIdMensajes = mensajeIdMensajes;
        this.idIntereses = intereses;
        this.idRelacionesEmpresas = relacionesEmpresas;
        this.idRoles = roleIdRols;
        this.idPalabraClaves = palabrasClaveIdPalabraClaves;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Short getTipoDocumentoUsuario() {
        return tipoDocumentoUsuario;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public String getNombresUsuario() {
        return nombresUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public Short getEstadoUsuario() {
        return estadoUsuario;
    }

    public Integer getUbicacionIdUbicacion() {
        return ubicacionIdUbicacion;
    }

    public Integer getAccesoIdUsuario() {
        return accesoIdUsuario;
    }

    public List<Integer> getIdArchivos() {
        return idArchivos;
    }

    public List<Integer> getIdImagenes() {
        return idImagenes;
    }

    public List<Integer> getIdAuditorias() {
        return idAuditorias;
    }

    public List<Integer> getIdPostulaciones() {
        return idPostulaciones;
    }

    public List<Integer> getMensajeIdMensajes() {
        return mensajeIdMensajes;
    }

    public List<Integer> getIdIntereses() {
        return idIntereses;
    }

    public List<Integer> getIdRelacionesEmpresas() {
        return idRelacionesEmpresas;
    }

    public List<Integer> getIdRoles() {
        return idRoles;
    }

    public List<Integer> getIdPalabraClaves() {
        return idPalabraClaves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDto entity = (UsuarioDto) o;
        return Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.tipoDocumentoUsuario, entity.tipoDocumentoUsuario) &&
                Objects.equals(this.documentoUsuario, entity.documentoUsuario) &&
                Objects.equals(this.nombresUsuario, entity.nombresUsuario) &&
                Objects.equals(this.apellidosUsuario, entity.apellidosUsuario) &&
                Objects.equals(this.estadoUsuario, entity.estadoUsuario) &&
                Objects.equals(this.ubicacionIdUbicacion, entity.ubicacionIdUbicacion) &&
                Objects.equals(this.accesoIdUsuario, entity.accesoIdUsuario) &&
                Objects.equals(this.idArchivos, entity.idArchivos) &&
                Objects.equals(this.idImagenes, entity.idImagenes) &&
                Objects.equals(this.idAuditorias, entity.idAuditorias) &&
                Objects.equals(this.idPostulaciones, entity.idPostulaciones) &&
                Objects.equals(this.mensajeIdMensajes, entity.mensajeIdMensajes) &&
                Objects.equals(this.idIntereses, entity.idIntereses) &&
                Objects.equals(this.idRelacionesEmpresas, entity.idRelacionesEmpresas) &&
                Objects.equals(this.idRoles, entity.idRoles) &&
                Objects.equals(this.idPalabraClaves, entity.idPalabraClaves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, tipoDocumentoUsuario, documentoUsuario, nombresUsuario, apellidosUsuario, estadoUsuario, ubicacionIdUbicacion, accesoIdUsuario, idArchivos, idImagenes, idAuditorias, idPostulaciones, mensajeIdMensajes, idIntereses, idRelacionesEmpresas, idRoles, idPalabraClaves);
    }

}