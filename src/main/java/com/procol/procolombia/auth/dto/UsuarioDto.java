package com.procol.procolombia.auth.dto;

import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.vacante.entities.IntereseId;
import com.procol.procolombia.vacante.entities.RelUsuariosEmpresaId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Usuario}
 */
public class UsuarioDto implements Serializable {
    private Integer id;
    private Integer idUbicacion;
    @NotNull
    private Short tipoDocumentoUsuario;
    @NotNull
    @Size(max = 150)
    private String documentoUsuario;
    @NotNull
    @Size(max = 50)
    private String nombresUsuario;
    @NotNull
    @Size(max = 50)
    private String apellidosUsuario;
    @NotNull
    private Short estadoUsuario;
    private Integer accesoId;
    private Set<Integer> archivoIds;
    private Set<Integer> imageneIds;
    private Set<IntereseId> intereseIds;
    private Set<Integer> mensajeIds;
    private Set<Integer> postulacioneIds;
    private Set<Integer> palabrasClaveIds;
    private Set<RelUsuariosEmpresaId> relUsuariosEmpresaIds;
    private Set<Integer> roleIds;

    public UsuarioDto(Integer id, Integer idUbicacionId, Short tipoDocumentoUsuario, String documentoUsuario, String nombresUsuario, String apellidosUsuario, Short estadoUsuario, Integer accesoId, Set<Integer> archivoIds, Set<Integer> imageneIds, Set<IntereseId> intereseIds, Set<Integer> mensajeIds, Set<Integer> postulacioneIds, Set<Integer> palabrasClaveIds, Set<RelUsuariosEmpresaId> relUsuariosEmpresaIds, Set<Integer> roleIds) {
        this.id = id;
        this.idUbicacion = idUbicacionId;
        this.tipoDocumentoUsuario = tipoDocumentoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.nombresUsuario = nombresUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.estadoUsuario = estadoUsuario;
        this.accesoId = accesoId;
        this.archivoIds = archivoIds;
        this.imageneIds = imageneIds;
        this.intereseIds = intereseIds;
        this.mensajeIds = mensajeIds;
        this.postulacioneIds = postulacioneIds;
        this.palabrasClaveIds = palabrasClaveIds;
        this.relUsuariosEmpresaIds = relUsuariosEmpresaIds;
        this.roleIds = roleIds;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
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

    public Integer getAccesoId() {
        return accesoId;
    }

    public Set<Integer> getArchivoIds() {
        return archivoIds;
    }

    public Set<Integer> getImageneIds() {
        return imageneIds;
    }

    public Set<IntereseId> getIntereseIds() {
        return intereseIds;
    }

    public Set<Integer> getMensajeIds() {
        return mensajeIds;
    }

    public Set<Integer> getPostulacioneIds() {
        return postulacioneIds;
    }

    public Set<Integer> getPalabrasClaveIds() {
        return palabrasClaveIds;
    }

    public Set<RelUsuariosEmpresaId> getRelUsuariosEmpresaIds() {
        return relUsuariosEmpresaIds;
    }

    public Set<Integer> getRoleIds() {
        return roleIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDto entity = (UsuarioDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idUbicacion, entity.idUbicacion) &&
                Objects.equals(this.tipoDocumentoUsuario, entity.tipoDocumentoUsuario) &&
                Objects.equals(this.documentoUsuario, entity.documentoUsuario) &&
                Objects.equals(this.nombresUsuario, entity.nombresUsuario) &&
                Objects.equals(this.apellidosUsuario, entity.apellidosUsuario) &&
                Objects.equals(this.estadoUsuario, entity.estadoUsuario) &&
                Objects.equals(this.accesoId, entity.accesoId) &&
                Objects.equals(this.archivoIds, entity.archivoIds) &&
                Objects.equals(this.imageneIds, entity.imageneIds) &&
                Objects.equals(this.intereseIds, entity.intereseIds) &&
                Objects.equals(this.mensajeIds, entity.mensajeIds) &&
                Objects.equals(this.postulacioneIds, entity.postulacioneIds) &&
                Objects.equals(this.palabrasClaveIds, entity.palabrasClaveIds) &&
                Objects.equals(this.relUsuariosEmpresaIds, entity.relUsuariosEmpresaIds) &&
                Objects.equals(this.roleIds, entity.roleIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUbicacion, tipoDocumentoUsuario, documentoUsuario, nombresUsuario, apellidosUsuario, estadoUsuario, accesoId, archivoIds, imageneIds, intereseIds, mensajeIds, postulacioneIds, palabrasClaveIds, relUsuariosEmpresaIds, roleIds);
    }
}