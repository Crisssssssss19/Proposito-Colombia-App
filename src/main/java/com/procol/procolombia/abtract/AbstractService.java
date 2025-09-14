package com.procol.procolombia.abtract;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T, ID> implements  Crud<T, ID> {

    protected abstract JpaRepository<T, ID> getRepository();

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public T update(ID id, T entity) {
        if (!getRepository().existsById(id)) {
            throw new RuntimeException("Entidad no encontrada con id: " + id);
        }
        return getRepository().save(entity);
    }

    @Override
    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }
}