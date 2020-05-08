package com.treetrunk.trek.service;

import com.treetrunk.trek.model.AbstractEntity;

import java.util.List;

public interface CommonService<E extends AbstractEntity> {

    List<E> getAll();

    E findById(Long id);

    E create(E entity);

    E update(E updateEntity, E entity);

    void delete(Long id);
}
