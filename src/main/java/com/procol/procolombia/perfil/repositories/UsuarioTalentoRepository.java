package com.procol.procolombia.perfil.repositories;

import com.procol.procolombia.perfil.entities.UsuarioTalento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioTalentoRepository extends JpaRepository<UsuarioTalento, Integer> {
    List<UsuarioTalento> findByUsuarioId(Integer idUsuario);

    boolean existsByUsuarioIdAndTalentoId(Integer usuarioId, Integer talentoId);

}
