package com.treetrunk.trek.service;

import com.treetrunk.trek.model.AbstractEntity;
import com.treetrunk.trek.repository.CommonRepository;
import org.springframework.beans.BeanUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
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
    public E update(E entity, E customEntity) {
        BeanUtils.copyProperties(customEntity, entity, "id");
        return repository.save(entity);
    }

    public E update(E entity, E customEntity, String[] ignoreProperties) {
        BeanUtils.copyProperties(customEntity, entity, ignoreProperties);
        return repository.save(entity);
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
