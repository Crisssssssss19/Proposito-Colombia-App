package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.PalabraClave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PalabraClaveRepository extends JpaRepository<PalabraClave, Integer> {
    Optional<PalabraClave> findByTextoPalabraClave(String textoPalabraClave);

    List<PalabraClave> findByTextoPalabraClaveContainingIgnoreCase(String texto);

    boolean existsByTextoPalabraClave(String textoPalabraClave);

    @Query("SELECT p FROM PalabraClave p JOIN p.usuarios u WHERE u.id = :usuarioId")
    List<PalabraClave> findByUsuario(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT p FROM PalabraClave p JOIN p.vacantes v WHERE v.id = :vacanteId")
    List<PalabraClave> findByVacante(@Param("vacanteId") Integer vacanteId);

    @Query("SELECT COUNT(u) FROM Usuario u JOIN u.palabrasClaves p WHERE p.id = :palabraId")
    long countUsuariosByPalabra(@Param("palabraId") Integer palabraId);

    @Query("SELECT COUNT(v) FROM Vacante v JOIN v.palabrasClaves p WHERE p.id = :palabraId")
    long countVacantesByPalabra(@Param("palabraId") Integer palabraId);

    @Query("SELECT p FROM PalabraClave p ORDER BY SIZE(p.usuarios) DESC")
    List<PalabraClave> findMostPopular();
}