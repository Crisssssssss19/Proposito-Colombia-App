package com.procol.procolombia.abtract;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio genérico para entidades.
 * @param <T>  Entidad
 * @param <ID> Tipo del ID de la entidad
 */
public interface Crud<T, ID>{

    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T entity);
    void deleteById(ID id);
    T update(ID id, T postulacioneDto);
    boolean existsById(ID id);

}
