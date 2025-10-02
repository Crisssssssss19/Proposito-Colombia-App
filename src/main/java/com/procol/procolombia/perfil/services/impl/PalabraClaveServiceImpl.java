package com.procol.procolombia.perfil.services.impl;

import com.procol.procolombia.perfil.dtos.request.SavePalabraClave;
import com.procol.procolombia.perfil.dtos.response.GetPalabraClave;
import com.procol.procolombia.perfil.mappers.PalabrasClaveMapper;
import com.procol.procolombia.perfil.services.PalabraClaveService;
import com.procol.procolombia.vacante.entities.PalabrasClave;
import com.procol.procolombia.vacante.repositories.PalabrasClaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalabraClaveServiceImpl implements PalabraClaveService {

    private PalabrasClaveRepository palabrasClaveRepository;
    private PalabrasClaveMapper palabraClaveMapper;

    public PalabraClaveServiceImpl(PalabrasClaveRepository palabrasClaveRepository, PalabrasClaveMapper palabraClaveMapper) {
        this.palabrasClaveRepository = palabrasClaveRepository;
        this.palabraClaveMapper = palabraClaveMapper;
    }

    @Override
    public GetPalabraClave crearPalabraClave(SavePalabraClave savePalabraClave) {
        palabrasClaveRepository.findByTextoPalabraClave(savePalabraClave.textoPalabraClave())
                .ifPresent(palabrasClave -> {
                    throw new IllegalArgumentException("La palabra clave con texto " + savePalabraClave.textoPalabraClave() + " ya existe.");
                });
        PalabrasClave palabrasClave = palabraClaveMapper.SavePalabraClaveToPalabraClave(savePalabraClave);
        palabrasClave = palabrasClaveRepository.save(palabrasClave);
        return palabraClaveMapper.PalabraClaveToGetPalabraClave(palabrasClave);
    }

    @Override
    public GetPalabraClave obtenerPalabraClavePorId(Integer id) {
        PalabrasClave palabrasClave = palabrasClaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La palabra clave con el id " + id + " no existe."));
        return palabraClaveMapper.PalabraClaveToGetPalabraClave(palabrasClave);
    }

    @Override
    public GetPalabraClave actualizarPalabraClave(Integer id, SavePalabraClave savePalabraClave) {
        PalabrasClave palabrasClave = palabrasClaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La palabra clave con el id " + id + " no existe."));

        palabrasClaveRepository.findByTextoPalabraClave(savePalabraClave.textoPalabraClave())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new IllegalArgumentException("La palabra clave con texto " + savePalabraClave.textoPalabraClave() + " ya existe.");
                    }
                });

        palabrasClave.setTextoPalabraClave(savePalabraClave.textoPalabraClave());
        palabrasClave = palabrasClaveRepository.save(palabrasClave);
        return palabraClaveMapper.PalabraClaveToGetPalabraClave(palabrasClave);
    }

    @Override
    public void eliminarPalabraClave(Integer id) {
        PalabrasClave palabrasClave = palabrasClaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La palabra clave con el id " + id + " no existe."));
        palabrasClaveRepository.delete(palabrasClave);
    }

    @Override
    public List<GetPalabraClave> listarPalabrasClave() {
        List<PalabrasClave> palabrasClaves = palabrasClaveRepository.findAll();
        if (!palabrasClaves.isEmpty()) {
            return palabraClaveMapper.ListPalabraClaveToListGetPalabraClave(palabrasClaves);
        }
        return palabraClaveMapper.ListPalabraClaveToListGetPalabraClave(palabrasClaves);
    }
}
