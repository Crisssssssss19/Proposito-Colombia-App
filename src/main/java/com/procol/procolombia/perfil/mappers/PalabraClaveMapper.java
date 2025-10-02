package com.procol.procolombia.perfil.mappers;

import com.procol.procolombia.perfil.dtos.request.SavePalabraClave;
import com.procol.procolombia.perfil.dtos.response.GetPalabraClave;
import com.procol.procolombia.vacante.entities.PalabrasClave;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PalabraClaveMapper {

    PalabrasClave SavePalabraClaveToPalabraClave(SavePalabraClave savePalabraClave);

    GetPalabraClave PalabraClaveToGetPalabraClave(PalabrasClave palabraClave);

    List<GetPalabraClave> ListPalabraClaveToListGetPalabraClave(List<PalabrasClave> palabrasClave);
}
