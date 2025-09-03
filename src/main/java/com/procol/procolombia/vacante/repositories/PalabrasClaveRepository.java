package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.PalabrasClave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PalabrasClaveRepository extends JpaRepository<PalabrasClave, Integer> {

    Optional<PalabrasClave> findByTextoPalabraClave(String textoPalabraClave);

    List<PalabrasClave> findByTextoPalabraClaveContainingIgnoreCase(String texto);

    boolean existsByTextoPalabraClave(String textoPalabraClave);

    @Query("SELECT p FROM PalabrasClave p JOIN p.usuarios u WHERE u.id = :usuarioId")
    List<PalabrasClave> findByUsuario(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT p FROM PalabrasClave p JOIN p.vacantes v WHERE v.id = :vacanteId")
    List<PalabrasClave> findByVacante(@Param("vacanteId") Integer vacanteId);

    @Query("SELECT COUNT(u) FROM Usuario u JOIN u.palabrasClaves p WHERE p.id = :palabraId")
    long countUsuariosByPalabra(@Param("palabraId") Integer palabraId);

    @Query("SELECT COUNT(v) FROM Vacante v JOIN v.palabrasClaves p WHERE p.id = :palabraId")
    long countVacantesByPalabra(@Param("palabraId") Integer palabraId);

    @Query("SELECT p FROM PalabrasClave p ORDER BY SIZE(p.usuarios) DESC")
    List<PalabrasClave> findMostPopular();

}