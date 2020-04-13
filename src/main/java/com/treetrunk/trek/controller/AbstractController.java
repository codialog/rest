package com.treetrunk.trek.controller;

import com.treetrunk.trek.model.AbstractEntity;
import com.treetrunk.trek.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public abstract class AbstractController<E extends AbstractEntity, S extends CommonService<E>> implements CommonController<E> {
    private final S service;

    protected AbstractController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<E> create(@RequestBody E entity) {
        E created = service.create(entity);
        if (created == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<E>> getAll() {
        List<E> list = service.getAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(list, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<E> findById(@PathVariable(name = "id") Long id) {
        E entity = service.findById(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.FOUND);
    }

    public ResponseEntity<E> update(@PathVariable(name = "id") Long id, @RequestBody E entity) {
        E updateEntity = service.findById(id);
        E updated = service.update(updateEntity, entity);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
