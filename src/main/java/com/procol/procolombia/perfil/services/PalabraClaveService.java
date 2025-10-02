package com.procol.procolombia.perfil.services;

import com.procol.procolombia.perfil.dtos.request.SavePalabraClave;
import com.procol.procolombia.perfil.dtos.response.GetPalabraClave;

import java.util.List;

public interface PalabraClaveService {

    GetPalabraClave crearPalabraClave(SavePalabraClave savePalabraClave);

    GetPalabraClave obtenerPalabraClavePorId(Integer id);

    GetPalabraClave actualizarPalabraClave(Integer id, SavePalabraClave savePalabraClave);

    void eliminarPalabraClave(Integer id);

    List<GetPalabraClave> listarPalabrasClave();
}
