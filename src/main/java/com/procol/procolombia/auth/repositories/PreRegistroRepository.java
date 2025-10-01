package com.procol.procolombia.auth.repositories;

import com.procol.procolombia.auth.entities.PreRegistro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreRegistroRepository extends JpaRepository<PreRegistro, String> {
    Optional<PreRegistro> findByIdPreRegistro(String idPreRegistro);
}
