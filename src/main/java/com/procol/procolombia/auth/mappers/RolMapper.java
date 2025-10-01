package com.procol.procolombia.auth.mappers;

import com.procol.procolombia.auth.dto.Response.RolResponseDTO;
import com.procol.procolombia.auth.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolMapper {

    RolResponseDTO toDto(Role role);
}
