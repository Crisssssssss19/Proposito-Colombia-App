package com.procol.procolombia.auth.dto;

import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.vacante.entities.InteresId;
import com.procol.procolombia.vacante.entities.RelUsuarioEmpresaId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Usuario}
 */
public class UsuarioDto implements Serializable {
    private final Integer id;
    private final Integer idUbicacion;
    @NotNull
    private final Short tipoDocumentoUsuario;
    @NotNull
    @Size(max = 150)
    private final String documentoUsuario;
    @NotNull
    @Size(max = 50)
    private final String nombresUsuario;
    @NotNull
    @Size(max = 50)
    private final String apellidosUsuario;
    @NotNull
    private final Short estadoUsuario;
    private final Integer accesoId;
    private final Set<Integer> archivoIds;
    private final Set<Integer> imageneIds;
    private final Set<InteresId> interesIds;
    private final Set<Integer> mensajeIds;
    private final Set<Integer> postulacioneIds;
    private final Set<Integer> palabrasClaveIds;
    private final Set<RelUsuarioEmpresaId> relUsuarioEmpresaIds;
    private final Set<Integer> roleIds;

    public UsuarioDto(Integer id, Integer idUbicacionId, Short tipoDocumentoUsuario, String documentoUsuario, String nombresUsuario, String apellidosUsuario, Short estadoUsuario, Integer accesoId, Set<Integer> archivoIds, Set<Integer> imageneIds, Set<InteresId> interesIds, Set<Integer> mensajeIds, Set<Integer> postulacioneIds, Set<Integer> palabrasClaveIds, Set<RelUsuarioEmpresaId> relUsuarioEmpresaIds, Set<Integer> roleIds) {
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
        this.interesIds = interesIds;
        this.mensajeIds = mensajeIds;
        this.postulacioneIds = postulacioneIds;
        this.palabrasClaveIds = palabrasClaveIds;
        this.relUsuarioEmpresaIds = relUsuarioEmpresaIds;
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

    public Set<InteresId> getIntereseIds() {
        return interesIds;
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

    public Set<RelUsuarioEmpresaId> getRelUsuariosEmpresaIds() {
        return relUsuarioEmpresaIds;
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
                Objects.equals(this.interesIds, entity.interesIds) &&
                Objects.equals(this.mensajeIds, entity.mensajeIds) &&
                Objects.equals(this.postulacioneIds, entity.postulacioneIds) &&
                Objects.equals(this.palabrasClaveIds, entity.palabrasClaveIds) &&
                Objects.equals(this.relUsuarioEmpresaIds, entity.relUsuarioEmpresaIds) &&
                Objects.equals(this.roleIds, entity.roleIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUbicacion, tipoDocumentoUsuario, documentoUsuario, nombresUsuario, apellidosUsuario, estadoUsuario, accesoId, archivoIds, imageneIds, interesIds, mensajeIds, postulacioneIds, palabrasClaveIds, relUsuarioEmpresaIds, roleIds);
    }
}