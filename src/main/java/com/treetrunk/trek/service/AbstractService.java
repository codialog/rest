package com.treetrunk.trek.service;

import com.treetrunk.trek.model.AbstractEntity;
import com.treetrunk.trek.repository.CommonRepository;
import org.springframework.beans.BeanUtils;

import java.util.List;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>> implements CommonService<E> {

    protected final R repository;

    protected AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public E create(E entity) {
        return repository.save(entity);
    }

    @Override
    public E update(E updateEntity, E entity) {
        BeanUtils.copyProperties(entity, updateEntity);
        return repository.save(updateEntity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<E> getAll() {
        return repository.findAll();
    }

    @Override
    public E findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
