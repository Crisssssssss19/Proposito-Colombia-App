package com.procol.procolombia.abtract;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<E, D, ID> implements Crud<D, ID> {
    protected abstract JpaRepository<E, ID> getEntityRepository();
    protected abstract D mapToDto(E entity);
    protected abstract E mapToEntity(D dto);
    protected abstract List<D> mapToDtoList(List<E> entities);
    protected abstract void updateEntityFromDto(E entity, D dto);

    @Override
    public List<D> findAll() {
        List<E> entities = getEntityRepository().findAll();
        return mapToDtoList(entities);
    }

    @Override
    public Optional<D> findById(ID id) {
        Optional<E> entity = getEntityRepository().findById(id);
        return entity.map(this::mapToDto);
    }

    @Override
    public D save(D dto) {
        E entity = mapToEntity(dto);
        E savedEntity = getEntityRepository().save(entity);
        return mapToDto(savedEntity);
    }

    @Override
    public void deleteById(ID id) {
        getEntityRepository().deleteById(id);
    }

    @Override
    public D update(ID id, D dto) {
        if (!getEntityRepository().existsById(id)) {
            throw new RuntimeException("Entidad no encontrada con id: " + id);
        }

        return getEntityRepository().findById(id)
                .map(existingEntity -> {
                    updateEntityFromDto(existingEntity, dto);
                    E savedEntity = getEntityRepository().save(existingEntity);
                    return mapToDto(savedEntity);
                })
                .orElseThrow(() -> new RuntimeException("Entidad no encontrada con id: " + id));
    }

    @Override
    public boolean existsById(ID id) {
        return getEntityRepository().existsById(id);
    }
}
