package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.EmpresaDto;
import com.procol.procolombia.vacante.entities.Empresa;
import com.procol.procolombia.vacante.entities.TipoEmpresa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {

    @Mapping(source = "idTipoEmpresa.id", target = "idTipoEmpresa")
    EmpresaDto toDto(Empresa empresa);

    @Mapping(source = "idTipoEmpresa", target = "idTipoEmpresa", qualifiedByName = "mapTipoEmpresa")
    Empresa toEntity(EmpresaDto dto);

    @Named("mapTipoEmpresa")
    default TipoEmpresa mapTipoEmpresa(Integer id) {
        if (id == null) return null;
        TipoEmpresa tipoEmpresa = new TipoEmpresa();
        tipoEmpresa.setId(id);
        return tipoEmpresa;
    }
}
