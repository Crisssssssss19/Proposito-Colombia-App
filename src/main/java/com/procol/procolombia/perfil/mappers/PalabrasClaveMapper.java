package com.procol.procolombia.perfil.mappers;

import com.procol.procolombia.perfil.dtos.request.SavePalabraClave;
import com.procol.procolombia.perfil.dtos.response.GetPalabraClave;
import com.procol.procolombia.vacante.entities.PalabrasClave;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PalabrasClaveMapper {

    PalabrasClave SavePalabraClaveToPalabraClave(SavePalabraClave savePalabraClave);

    GetPalabraClave PalabraClaveToGetPalabraClave(PalabrasClave palabraClave);

    List<GetPalabraClave> ListPalabraClaveToListGetPalabraClave(List<PalabrasClave> palabrasClave);

    List<PalabrasClave>  ListGetPalabraClaveToListPalabraClave(List<GetPalabraClave> getPalabraClave);
}
