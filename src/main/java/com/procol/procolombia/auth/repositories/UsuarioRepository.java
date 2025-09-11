package com.procol.procolombia.auth.repositories;

import com.procol.procolombia.auth.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  Optional<Usuario> findByDocumentoUsuario(String documentoUsuario);
  List<Usuario> findByNombresUsuario(String nombresUsuario);
  List<Usuario> findByTipoDocumentoUsuario(Short tipoUsuario);

  List<Usuario> findByEstadoUsuario(Short estadoUsuario);

  List<Usuario> findByIdUbicacion_Id(Integer idUbicacion);

  boolean existsByDocumentoUsuario(String documentoUsuario);

  Page<Usuario> findByEstadoUsuario(Short estadoUsuario, Pageable pageable);

  @Query("SELECT u FROM Usuario u WHERE u.nombresUsuario LIKE %:nombre% OR u.apellidosUsuario LIKE %:nombre%")
  Page<Usuario> findByNombreContaining(@Param("nombre") String nombre, Pageable pageable);

  @Query("SELECT u FROM Usuario u JOIN u.roles r WHERE r.id = :idRol")
  List<Usuario> findByRol(@Param("idRol") Integer idRol);

  @Modifying
  @Query("UPDATE Usuario u SET u.estadoUsuario = :estado WHERE u.id = :id")
  void updateEstado(@Param("id") Integer id, @Param("estado") Short estado);

  @Query("SELECT COUNT(u) FROM Usuario u WHERE u.estadoUsuario = 1")
  long countActiveUsers();

  @Query("SELECT u FROM Usuario u JOIN u.palabrasClaves p WHERE p.textoPalabraClave IN :palabras")
  List<Usuario> findByPalabrasClave(@Param("palabras") List<String> palabras);
}
