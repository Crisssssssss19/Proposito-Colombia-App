package com.procol.procolombia.abtract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Repositorio genérico para entidades.
 * @param <T>  Entidad
 * @param <ID> Tipo del ID de la entidad
 */
@NoRepositoryBean
public interface AbstractRepository<T, ID> extends JpaRepository<T, ID> {
}
