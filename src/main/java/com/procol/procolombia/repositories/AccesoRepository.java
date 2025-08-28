package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Acceso;
import org.apache.tomcat.util.http.parser.AcceptEncoding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccesoRepository extends JpaRepository<Acceso, Integer> {
    Optional<Acceso> findByCorreoAcceso(String correoAcceso);
    Optional<Acceso> findByTelefonoAcceso(String telefonoAcceso);
    Optional<Acceso> findByUuidAcceso(String uuidAcceso);
    boolean existsByCorreoAcceso(String correoAcceso);
    boolean existsByTelefonoAcceso(String telefonoAcceso);
    @Query("SELECT a FROM Acceso a WHERE a.correoAcceso = :correo OR a.telefonoAcceso = :telefono")
    Optional<Acceso> findByCorreoAccesoOrTelefonoAcceso(@Param("correo") String correo, @Param("telefono") String telefono);
}