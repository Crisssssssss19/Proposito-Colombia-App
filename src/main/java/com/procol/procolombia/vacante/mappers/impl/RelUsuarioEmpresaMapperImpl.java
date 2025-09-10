package com.procol.procolombia.vacante.mappers.impl;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.vacante.dto.RelUsuarioEmpresaDto;
import com.procol.procolombia.vacante.entities.Empresa;
import com.procol.procolombia.vacante.entities.RelUsuarioEmpresa;
import com.procol.procolombia.vacante.entities.RelUsuarioEmpresaId;
import com.procol.procolombia.vacante.mappers.RelUsuarioEmpresaMapper;

import java.util.Set;
import java.util.stream.Collectors;

public class RelUsuarioEmpresaMapperImpl implements RelUsuarioEmpresaMapper {

    @Override
    public RelUsuarioEmpresa toEntity(RelUsuarioEmpresaDto dto) {
        if (dto == null) {
            return null;
        }
        RelUsuarioEmpresa entity = new RelUsuarioEmpresa();
        RelUsuarioEmpresaId id = new RelUsuarioEmpresaId();
        id.setIdUsuario(dto.idUsuario());
        id.setIdEmpresa(dto.idEmpresa());
        entity.setId(id);
        if (dto.idUsuario() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(dto.idUsuario());
            entity.setIdUsuario(usuario);
        }

        if (dto.idEmpresa() != null) {
            Empresa empresa = new Empresa();
            empresa.setId(dto.idEmpresa());
            entity.setIdEmpresa(empresa);
        }
        entity.setPermisoRelUsuarioEmpresa(dto.permisoRelUsuarioEmpresa());

        return entity;
    }

    @Override
    public RelUsuarioEmpresaDto toDto(RelUsuarioEmpresa rel) {
        if (rel == null) {
            return null;
        }

        RelUsuarioEmpresaId id = rel.getId();
        Integer idUsuario = null;
        if (rel.getIdUsuario() != null) {
            idUsuario = rel.getIdUsuario().getId();
        }
        Integer idEmpresa = null;
        if (rel.getIdEmpresa() != null) {
            idEmpresa = rel.getIdEmpresa().getId();
        }

        Set<Integer> vacanteIds = Set.of();
        if (rel.getVacantes() != null) {
            vacanteIds = rel.getVacantes().stream()
                    .map(vacante -> vacante.getId())
                    .collect(Collectors.toSet());
        }

        return new RelUsuarioEmpresaDto(
                id,
                idUsuario,
                idEmpresa,
                rel.getPermisoRelUsuarioEmpresa(),
                vacanteIds
        );
    }

    @Override
    public Set<RelUsuarioEmpresa> toListEntity(Set<RelUsuarioEmpresaDto> relUsuarioEmpresaDtos) {
        return Set.of();
    }

    @Override
    public Set<RelUsuarioEmpresaDto> toListDto(Set<RelUsuarioEmpresa> relUsuarioEmpresas) {
        return Set.of();
    }
}
