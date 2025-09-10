package com.procol.procolombia.vacante.Services.Impl;

import com.procol.procolombia.vacante.Services.RelUsuarioEmpresaService;
import com.procol.procolombia.vacante.dto.RelUsuarioEmpresaDto;
import com.procol.procolombia.vacante.entities.RelUsuarioEmpresaId;
import com.procol.procolombia.vacante.mappers.RelUsuarioEmpresaMapper;
import com.procol.procolombia.vacante.repositories.RelUsuarioEmpresaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelUsuarioEmpresaServiceImpl implements RelUsuarioEmpresaService {

    private final RelUsuarioEmpresaRepository relUsuarioEmpresaRepository;
    private final RelUsuarioEmpresaMapper relUsuarioEmpresaMapper;

    public RelUsuarioEmpresaServiceImpl(RelUsuarioEmpresaRepository relUsuarioEmpresaRepository, RelUsuarioEmpresaMapper relUsuarioEmpresaMapper) {
        this.relUsuarioEmpresaRepository = relUsuarioEmpresaRepository;
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
    @Transactional(readOnly = true)
    public RelUsuarioEmpresaDto findUsuarioEmpresasByUsuarioId(Integer usuarioId) {
        return relUsuarioEmpresaRepository.findByUsuarioId(usuarioId)
                .map(relUsuarioEmpresaMapper::toDto)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public RelUsuarioEmpresaDto findUsuarioEmpresaByEmpresaId(Integer empresaId) {
        return relUsuarioEmpresaRepository.findByEmpresaId(empresaId)
                .map(relUsuarioEmpresaMapper::toDto)
                .orElse(null);
    }

    @Override
    public void deleteUsuarioEmpresasByUsuarioId(Integer usuarioId, Integer empresaId) {
        RelUsuarioEmpresaId id = new RelUsuarioEmpresaId();
        id.setIdUsuario(usuarioId);
        id.setIdEmpresa(empresaId);

        if (relUsuarioEmpresaRepository.existsById(id)) {
            relUsuarioEmpresaRepository.deleteById(id);
        }
    }
}
