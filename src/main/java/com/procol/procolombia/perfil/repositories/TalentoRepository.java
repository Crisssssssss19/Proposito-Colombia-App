package com.procol.procolombia.perfil.repositories;

import com.procol.procolombia.perfil.entities.Talento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TalentoRepository extends JpaRepository<Talento, Integer> {
    // Buscar talento por nombre y tipo (habilidad o competencia)
    Optional<Talento> findByNombreAndTipo(String nombre, Short tipo);

    // Buscar talentos por tipo (habilidad o competencia)
    List<Talento> findByTipo(Short tipo);

    // Buscar talento por ID
    Optional<Talento> findById(Integer id);

    // Listar todas las habilidades (tipo = 1)
    @Query("SELECT t FROM Talento t WHERE t.tipo = 1 ORDER BY t.nombre")
    List<Talento> findAllHabilidades();

    // Listar todas las competencias (tipo = 2)
    @Query("SELECT t FROM Talento t WHERE t.tipo = 2 ORDER BY t.nombre")
    List<Talento> findAllCompetencias();

    // Buscar talentos por nombre (para sugerencias/búsqueda)
    @Query("SELECT t FROM Talento t WHERE LOWER(t.nombre) LIKE LOWER(CONCAT('%', :query, '%')) AND t.tipo = :tipo")
    List<Talento> searchByNombreAndTipo(@Param("query") String query, @Param("tipo") Short tipo);
}
