package com.procol.procolombia.vacante.services.Impl;

import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.vacante.services.RelUsuarioEmpresaService;
import com.procol.procolombia.vacante.dto.RelUsuarioEmpresaDto;
import com.procol.procolombia.vacante.entities.*;
import com.procol.procolombia.vacante.mappers.RelUsuarioEmpresaMapper;
import com.procol.procolombia.vacante.repositories.EmpresaRepository;
import com.procol.procolombia.vacante.repositories.RelUsuarioEmpresaRepository;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelUsuarioEmpresaServiceImpl implements RelUsuarioEmpresaService {

    private final RelUsuarioEmpresaRepository relUsuarioEmpresaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;
    private final RelUsuarioEmpresaMapper relUsuarioEmpresaMapper;

    public RelUsuarioEmpresaServiceImpl(RelUsuarioEmpresaRepository relUsuarioEmpresaRepository, UsuarioRepository usuarioRepository, EmpresaRepository empresaRepository, RelUsuarioEmpresaMapper relUsuarioEmpresaMapper) {
        this.relUsuarioEmpresaRepository = relUsuarioEmpresaRepository;
        this.usuarioRepository = usuarioRepository;
        this.empresaRepository = empresaRepository;
        this.relUsuarioEmpresaMapper = relUsuarioEmpresaMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RelUsuarioEmpresaDto> getAllUsuariosEmpresas() {
        return relUsuarioEmpresaRepository.findAll()
                .stream()
                .map(relUsuarioEmpresaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RelUsuarioEmpresaDto createRelUsuarioEmpresa(RelUsuarioEmpresaDto relUsuarioEmpresaDto) {
        Empresa empresa = empresaRepository.findById(relUsuarioEmpresaDto.id().idEmpresa())
                .orElseThrow(() -> new RuntimeException("Id de empresa no encontrada: " + relUsuarioEmpresaDto.id().idEmpresa()));
        Usuario usuario = usuarioRepository.findById(relUsuarioEmpresaDto.id().idUsuario())
                .orElseThrow(() -> new RuntimeException("Id de usuario no encontrada: " + relUsuarioEmpresaDto.id().idUsuario()));

        RelUsuarioEmpresa entidad = new RelUsuarioEmpresa();
        RelUsuarioEmpresaId id = new RelUsuarioEmpresaId();
        id.setIdEmpresa(empresa.getId());
        id.setIdUsuario(usuario.getId());
        entidad.setId(id);
        entidad.setIdEmpresa(empresa);
        entidad.setIdUsuario(usuario);
        entidad.setPermisoRelUsuarioEmpresa(relUsuarioEmpresaDto.permisoRelUsuarioEmpresa());

        RelUsuarioEmpresa saved = relUsuarioEmpresaRepository.save(entidad);
        return relUsuarioEmpresaMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public RelUsuarioEmpresaDto findUsuarioEmpresasByUsuarioId(Integer usuarioId) {
        return relUsuarioEmpresaRepository.findById_IdUsuario(usuarioId)
                .map(relUsuarioEmpresaMapper::toDto)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public RelUsuarioEmpresaDto findUsuarioEmpresaByEmpresaId(Integer empresaId) {
        return relUsuarioEmpresaRepository.findById_IdEmpresa(empresaId)
                .map(relUsuarioEmpresaMapper::toDto)
                .orElse(null);
    }

    @Override
    public RelUsuarioEmpresaDto findUsuarioEmpresaByEmpresaIdAndUsuarioId(Integer empresaId, Integer usuarioId) {
        return relUsuarioEmpresaMapper.toDto(relUsuarioEmpresaRepository.findByIdUsuarioAndIdEmpresa(usuarioId,empresaId).orElse(null));
    }

    @Override
    public void deleteUsuarioEmpresasByUsuarioId(Integer idUsuario, Integer idEmpresa) {
        RelUsuarioEmpresaId id = new RelUsuarioEmpresaId();
        id.setIdEmpresa(idEmpresa);
        id.setIdUsuario(idUsuario);
        if (relUsuarioEmpresaRepository.existsById(id)) {
            relUsuarioEmpresaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Relación no encontrada");
        }
    }

}
