package com.procol.procolombia.perfil.services.impl;

import com.procol.procolombia.auth.entities.Acceso;
import com.procol.procolombia.auth.repositories.AccesoRepository;
import com.procol.procolombia.perfil.dtos.request.SaveAcceso;
import com.procol.procolombia.perfil.dtos.response.GetAcceso;
import com.procol.procolombia.perfil.mappers.AccesoMapper;
import com.procol.procolombia.perfil.services.AccesoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccesoServiceImpl implements AccesoService {
    private final AccesoRepository accesoRepository;
    private final AccesoMapper accesoMapper;

    public AccesoServiceImpl(AccesoRepository accesoRepository, AccesoMapper accesoMapper) {
        this.accesoRepository = accesoRepository;
        this.accesoMapper = accesoMapper;
    }

    @Override
    public GetAcceso crearAcceso(SaveAcceso saveAcceso) {
        Acceso acceso = accesoMapper.saveAccesoToAcceso(saveAcceso);
        if (accesoRepository.existsByCorreoAcceso(acceso.getCorreoAcceso())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }
        Acceso accesoGuardado = accesoRepository.save(acceso);
        return accesoMapper.accesoToGetAcceso(accesoGuardado);
    }

    @Override
    public GetAcceso actualizarAcceso(Integer idUsuario, SaveAcceso saveAcceso) {
        Acceso acceso = accesoRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Acceso no encontrado"));
        acceso.setTelefonoAcceso(saveAcceso.telefonoAcceso());
        acceso.setCorreoAcceso(saveAcceso.correoAcceso());
        acceso.setClaveAcceso(saveAcceso.claveAcceso());
        Acceso accesoActualizado = accesoRepository.save(acceso);
        return accesoMapper.accesoToGetAcceso(accesoActualizado);
    }

    @Override
    public GetAcceso obtenerAccesoPorUsuarioId(Integer idUsuario) {
        Acceso acceso = accesoRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Acceso no encontrado"));
        return accesoMapper.accesoToGetAcceso(acceso);
    }

    @Override
    public List<GetAcceso> lisatarAccesos() {
        return accesoMapper.accesosToGetAccesos(accesoRepository.findAll());
    }


    @Override
    public void eliminarAcceso(Integer idUsuario) {
        if (!accesoRepository.existsById(idUsuario)) {
            throw new RuntimeException("Acceso no encontrado");
        }
        accesoRepository.deleteById(idUsuario);
    }
}
