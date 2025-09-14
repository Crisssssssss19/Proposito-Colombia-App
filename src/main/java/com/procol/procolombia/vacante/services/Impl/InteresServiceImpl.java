package com.procol.procolombia.vacante.services.Impl;

import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.vacante.services.InteresService;
import com.procol.procolombia.vacante.dto.InteresDto;
import com.procol.procolombia.vacante.entities.Empresa;
import com.procol.procolombia.vacante.entities.Interes;
import com.procol.procolombia.vacante.entities.InteresId;
import com.procol.procolombia.vacante.mappers.InteresMapper;
import com.procol.procolombia.vacante.repositories.EmpresaRepository;
import com.procol.procolombia.vacante.repositories.InteresRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InteresServiceImpl implements InteresService {
    private final InteresRepository interesRepository;
    private final InteresMapper interesMapper;
    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;


    public InteresServiceImpl(InteresRepository interesRepository, InteresMapper interesMapper, EmpresaRepository empresaRepository, UsuarioRepository usuarioRepository) {
        this.interesRepository = interesRepository;
        this.interesMapper = interesMapper;
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public InteresDto createInteres(InteresDto interesDto) {
        Empresa empresa = empresaRepository.findById(interesDto.idEmpresa())
                .orElseThrow(() -> new RuntimeException("Id de empresa no encontrada: " + interesDto.idEmpresa()));
        Usuario usuario = usuarioRepository.findById(interesDto.idUsuario())
                .orElseThrow(() -> new RuntimeException("Id de usuario no encontrada: " + interesDto.idUsuario()));

        Interes entidad = new Interes();
        InteresId id = new InteresId();
        id.setIdEmpresa(empresa.getId());
        id.setIdUsuario(usuario.getId());
        entidad.setId(id);
        entidad.setIdEmpresa(empresa);
        entidad.setIdUsuario(usuario);
        entidad.setTipoInteres(interesDto.tipoInteres());

        Interes saved = interesRepository.save(entidad);
        return interesMapper.toDto(saved);
    }

    @Override
    public InteresDto updateInteres(InteresDto interesDto) {
        if (interesDto == null || interesDto.id() == null) {
            throw new IllegalArgumentException("El ID compuesto no puede ser nulo");
        }

        Interes existing = interesRepository.findById(interesDto.id())
                .orElseThrow(() -> new EntityNotFoundException("El interés con empresa " + interesDto.idEmpresa()+ " y usuario " + interesDto.idUsuario() + " no existe"));

        existing.setTipoInteres(interesDto.tipoInteres());

        Interes saved = interesRepository.save(existing);
        return interesMapper.toDto(saved);

    }

    @Override
    public Optional<InteresDto> getInteresById(Integer idUsuario, Integer idEmpresa) {
        InteresId id = new InteresId(idEmpresa, idUsuario);
        id.setIdEmpresa(idEmpresa);
        id.setIdUsuario(idUsuario);
        return interesRepository.findById(id).map(interesMapper::toDto);
    }

    @Override
    public void deleteInteresById(Integer idUsuario, Integer idEmpresa) {
        InteresId id = new InteresId();
        id.setIdEmpresa(idEmpresa);
        id.setIdUsuario(idUsuario);
        if (interesRepository.existsById(id)) {
            interesRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Relación no encontrada");
        }
    }

    @Override
    public Short obtenerTipoInteres(Integer idEmpresa, Integer idUsuario) {
        return interesRepository.findTipoInteresByEmpresaAndUsuario(idEmpresa, idUsuario)
                .orElse((short) 0);
    }

    @Override
    public List<Integer> obtenerEmpresasSeguidasporUsuario(Integer idUsuario, Short tipoInteres) {
        if (tipoInteres == null || tipoInteres == 0) return Collections.emptyList();
        return interesRepository.findEmpresasByUsuarioAndTipo(idUsuario, tipoInteres);
    }

    @Override
    public List<Integer> obtenerUsuariosSeguidasporEmpresa(Integer idEmpresa, Short tipoInteres) {
        if (tipoInteres == null || tipoInteres == 0) return Collections.emptyList();
        return interesRepository.findUsuariosByEmpresaAndTipo(idEmpresa, tipoInteres);
    }

    @Override
    public List<InteresDto> obtenerRelacionesMutuas() {
        return interesRepository.findByTipoInteres((short) 3)
                .stream()
                .map(interesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void usuarioSigueEmpresa(Integer idUsuario, Integer idEmpresa) {
        InteresId id = new InteresId();
        id.setIdEmpresa(idEmpresa);
        id.setIdUsuario(idUsuario);

        Interes interes = interesRepository.findById(id).orElseGet(() -> {
            Interes i = new Interes();
            i.setId(id);
            Empresa e = new Empresa(); e.setId(idEmpresa);
            Usuario u = new Usuario(); u.setId(idUsuario);
            i.setIdEmpresa(e);
            i.setIdUsuario(u);
            i.setTipoInteres((short) 0);
            return i;
        });

        Short tipoActual = interes.getTipoInteres() == null ? (short) 0 : interes.getTipoInteres();
        if (tipoActual == 0) {
            interes.setTipoInteres((short) 1);
        } else if (tipoActual == 2) {
            interes.setTipoInteres((short) 3);
        } else if (tipoActual == 1) {
            throw new IllegalStateException("El usuario ya sigue a esta empresa");
        }
        interesRepository.save(interes);
    }

    @Override
    public void empresaSigueUsuario(Integer idEmpresa, Integer idUsuario) {
        InteresId id = new InteresId();
        id.setIdEmpresa(idEmpresa);
        id.setIdUsuario(idUsuario);

        Interes interes = interesRepository.findById(id).orElseGet(() -> {
            Interes i = new Interes();
            i.setId(id);
            Empresa e = new Empresa(); e.setId(idEmpresa);
            Usuario u = new Usuario(); u.setId(idUsuario);
            i.setIdEmpresa(e);
            i.setIdUsuario(u);
            i.setTipoInteres((short) 0);
            return i;
        });

        Short tipoActual = interes.getTipoInteres() == null ? (short) 0 : interes.getTipoInteres();
        if (tipoActual == 0) {
            interes.setTipoInteres((short) 2); // empresa sigue
        } else if (tipoActual == 1) {
            interes.setTipoInteres((short) 3); // mutuo
        } else if (tipoActual == 2) {
            throw new IllegalStateException("La empresa ya sigue a este usuario");
        }
        interesRepository.save(interes);
    }

    @Override
    public boolean esSeguimientoMutuo(Integer idEmpresa, Integer idUsuario) {
        Short tipoInteres = obtenerTipoInteres(idEmpresa, idUsuario);
        if (Short.valueOf((short)3).equals(tipoInteres)){
            return true;
        }else{
            return false;
        }
    }
}
