package com.procol.procolombia.perfil.services.impl;

import com.procol.procolombia.perfil.dtos.request.SavePalabraClave;
import com.procol.procolombia.perfil.dtos.response.GetPalabraClave;
import com.procol.procolombia.perfil.mappers.PerfilPalabraClaveMapper;
import com.procol.procolombia.perfil.services.PalabraClaveService;
import com.procol.procolombia.vacante.entities.PalabraClave;
import com.procol.procolombia.vacante.repositories.PalabraClaveRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("perfilPalabraClaveServiceImpl")
public class PalabraClaveServiceImpl implements PalabraClaveService {

    private PalabraClaveRepository palabrasClaveRepository;
    private PerfilPalabraClaveMapper palabraClaveMapper;

    public PalabraClaveServiceImpl(PalabraClaveRepository palabrasClaveRepository, PerfilPalabraClaveMapper palabraClaveMapper) {
        this.palabrasClaveRepository = palabrasClaveRepository;
        this.palabraClaveMapper = palabraClaveMapper;
    }

    @Override
    public GetPalabraClave crearPalabraClave(SavePalabraClave savePalabraClave) {
        palabrasClaveRepository.findByTextoPalabraClave(savePalabraClave.textoPalabraClave())
                .ifPresent(palabrasClave -> {
                    throw new IllegalArgumentException("La palabra clave con texto " + savePalabraClave.textoPalabraClave() + " ya existe.");
                });
        PalabraClave palabrasClave = palabraClaveMapper.SavePalabraClaveToPalabraClave(savePalabraClave);
        palabrasClave = palabrasClaveRepository.save(palabrasClave);
        return palabraClaveMapper.PalabraClaveToGetPalabraClave(palabrasClave);
    }

    @Override
    public GetPalabraClave obtenerPalabraClavePorId(Integer id) {
        PalabraClave palabrasClave = palabrasClaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La palabra clave con el id " + id + " no existe."));
        return palabraClaveMapper.PalabraClaveToGetPalabraClave(palabrasClave);
    }

    @Override
    public GetPalabraClave actualizarPalabraClave(Integer id, SavePalabraClave savePalabraClave) {
        PalabraClave palabrasClave = palabrasClaveRepository.findById(id)
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
        PalabraClave palabrasClave = palabrasClaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La palabra clave con el id " + id + " no existe."));
        palabrasClaveRepository.delete(palabrasClave);
    }

    @Override
    public List<GetPalabraClave> listarPalabrasClave() {
        List<PalabraClave> palabrasClaves = palabrasClaveRepository.findAll();
        if (!palabrasClaves.isEmpty()) {
            return palabraClaveMapper.ListPalabraClaveToListGetPalabraClave(palabrasClaves);
        }
        return palabraClaveMapper.ListPalabraClaveToListGetPalabraClave(palabrasClaves);
    }

    @Override
    public List<PalabraClave> asignarPalabras(List<String> palabrasClave) {
        if (palabrasClave == null || palabrasClave.isEmpty()) {
            return Collections.emptyList();
        }

        return palabrasClave.stream()
                .map(String::trim)
                .map(this::obtenerOcrearPalabraClave)
                .toList();
    }

    private PalabraClave obtenerOcrearPalabraClave(String texto) {
        String textoNormalizado = texto.trim().toLowerCase();
        return palabrasClaveRepository.findByTextoPalabraClave(textoNormalizado)
                .orElseGet(() -> {
                    PalabraClave nuevaPalabraClave = new PalabraClave();
                    nuevaPalabraClave.setTextoPalabraClave(textoNormalizado);
                    return palabrasClaveRepository.save(nuevaPalabraClave);
                });
    }
}
