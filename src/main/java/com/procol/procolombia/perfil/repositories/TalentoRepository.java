package com.procol.procolombia.perfil.repositories;

import com.procol.procolombia.perfil.entities.Talento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TalentoRepository extends JpaRepository<Talento, Integer> {
    Optional<Talento> findByNombreAndTipo(String nombre, Short tipo);
    List<Talento> findByTipo(Short tipo);
    Optional<Talento> findById(Integer id);
    boolean existsByNombreAndTipo(String nombre, Short tipo);
}
