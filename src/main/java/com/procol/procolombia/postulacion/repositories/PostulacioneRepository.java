package com.procol.procolombia.postulacion.repositories;

import com.procol.procolombia.postulacion.entities.Postulacione;
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
public interface PostulacioneRepository extends JpaRepository<Postulacione, Integer> {

}