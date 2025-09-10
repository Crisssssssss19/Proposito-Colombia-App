package com.procol.procolombia.auth.mappers;

import com.procol.procolombia.auth.dto.RoleDto;
import com.procol.procolombia.auth.entities.Role;
import com.procol.procolombia.postulacion.dto.ArchivoDto;
import com.procol.procolombia.postulacion.entities.Archivo;

import java.util.List;
import java.util.stream.Collectors;

public class RolMapper {

    public static RoleDto toDto(Role role){
        if (role == null) return null;
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setEstadoRol(role.getEstadoRol());
        roleDto.setNombreRol(role.getNombreRol());
        return roleDto;
    }

    public static Role toEntity(RoleDto roleDto){
        if (roleDto == null) return null;
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setEstadoRol(roleDto.getEstadoRol());
        role.setNombreRol(roleDto.getNombreRol());
        return role;
    }

    public static List<RoleDto> toDto(List<Role> roles){
        return roles == null ? null : roles.stream().map(RolMapper::toDto).collect(Collectors.toList());
    }
    public static List<Role> toEntity(List<RoleDto> roleDto){
        return roleDto == null ? null : roleDto.stream().map(RolMapper::toEntity).collect(Collectors.toList());
    }
}
