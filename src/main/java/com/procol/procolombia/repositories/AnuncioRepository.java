package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {
}