package com.procol.procolombia.auth.repositories;

import com.procol.procolombia.auth.entities.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Integer> {

    List<Imagen> findByIdUsuario_Id(Integer idUsuario);

    List<Imagen> findByFavoritaImagen(Short favoritaImagen);

    Optional<Imagen> findByIdUsuario_IdAndFavoritaImagen(Integer idUsuario, Short favoritaImagen);

    @Query("SELECT i FROM Imagen i WHERE i.idUsuario.id = :usuarioId AND i.favoritaImagen = 1")
    Optional<Imagen> findImagenFavoritaByUsuario(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT COUNT(i) FROM Imagen i WHERE i.idUsuario.id = :usuarioId")
    long countByUsuario(@Param("usuarioId") Integer usuarioId);

    List<Imagen> findByTipoImagen(String tipoImagen);

}