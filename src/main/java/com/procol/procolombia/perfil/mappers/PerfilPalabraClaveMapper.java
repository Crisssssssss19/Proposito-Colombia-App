package com.procol.procolombia.perfil.mappers;

import com.procol.procolombia.perfil.dtos.request.SavePalabraClave;
import com.procol.procolombia.perfil.dtos.response.GetPalabraClave;
import com.procol.procolombia.vacante.entities.PalabraClave;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PerfilPalabraClaveMapper {

    PalabraClave SavePalabraClaveToPalabraClave(SavePalabraClave savePalabraClave);

    GetPalabraClave PalabraClaveToGetPalabraClave(PalabraClave palabraClave);

    List<GetPalabraClave> ListPalabraClaveToListGetPalabraClave(List<PalabraClave> palabrasClave);

    List<PalabraClave>  ListGetPalabraClaveToListPalabraClave(List<GetPalabraClave> getPalabraClave);
}
