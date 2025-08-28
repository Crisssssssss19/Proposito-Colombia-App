package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Imagene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageneRepository extends JpaRepository<Imagene, Integer> {

    List<Imagene> findByIdUsuario_Id(Integer idUsuario);

    List<Imagene> findByFavoritaImagen(Short favoritaImagen);

    Optional<Imagene> findByIdUsuario_IdAndFavoritaImagen(Integer idUsuario, Short favoritaImagen);

    @Query("SELECT i FROM Imagene i WHERE i.idUsuario.id = :usuarioId AND i.favoritaImagen = 1")
    Optional<Imagene> findImagenFavoritaByUsuario(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT COUNT(i) FROM Imagene i WHERE i.idUsuario.id = :usuarioId")
    long countByUsuario(@Param("usuarioId") Integer usuarioId);

    List<Imagene> findByTipoImagen(String tipoImagen);

}