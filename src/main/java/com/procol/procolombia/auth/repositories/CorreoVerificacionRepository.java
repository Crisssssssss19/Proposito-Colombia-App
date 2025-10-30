package com.procol.procolombia.auth.repositories;

import com.procol.procolombia.auth.entities.CorreoVerificacion;
import com.procol.procolombia.auth.entities.PreRegistro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorreoVerificacionRepository extends JpaRepository<CorreoVerificacion, String> {
    Optional<CorreoVerificacion> findByIdCorreo(String idCorreo);
}
