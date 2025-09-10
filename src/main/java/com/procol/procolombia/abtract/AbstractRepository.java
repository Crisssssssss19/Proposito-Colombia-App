package com.procol.procolombia.abtract;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Servicio abstracto genérico con operaciones CRUD.
 * @param <T>  Entidad
 * @param <ID> Tipo del ID de la entidad
 */
public interface  AbstractRepository<T, ID> extends JpaRepository<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    /**T  save(T entity);*/

    void deleteById(ID id);

    Page<T> findAll(Pageable pageable);
}