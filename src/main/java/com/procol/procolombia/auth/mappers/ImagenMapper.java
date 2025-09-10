package com.procol.procolombia.auth.mappers;

import com.procol.procolombia.auth.dto.Response.ImagenResponseDTO;
import com.procol.procolombia.auth.entities.Imagene;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagenMapper {

    ImagenResponseDTO toDto(Imagene imagene);
}
