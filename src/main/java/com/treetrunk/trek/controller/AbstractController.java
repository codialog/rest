package com.treetrunk.trek.controller;

import com.treetrunk.trek.model.AbstractEntity;
import com.treetrunk.trek.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractController<E extends AbstractEntity, S extends CommonService<E>> implements CommonController<E> {
    Logger logger = Logger.getLogger("Class AbstractController");
    private final S service;

    protected AbstractController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<E>> getAll() {
        logger.info("Inside getAll ....");
        List<E> list = service.getAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(list, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<E> findById(@PathVariable(name = "id") Long id) {
        logger.info("Inside findById ....");
        E entity = service.findById(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<E> create(@RequestBody E entity) {
        logger.info("Inside create ....");
        E created = service.create(entity);
        if (created == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    public ResponseEntity<E> update(@PathVariable(name = "id") Long id, @RequestBody E customEntity) {
        logger.info("Inside update ....");
        E entity = service.findById(id);
        E updatedEntity = service.update(entity, customEntity);
        if (updatedEntity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }

    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        logger.info("Inside delete ....");
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
