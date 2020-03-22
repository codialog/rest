package com.treetrunk.trek.controller;

import com.treetrunk.trek.model.AbstractEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CommonController<E extends AbstractEntity> {

    @PostMapping
    ResponseEntity<E> create(@RequestBody E entity);

    @GetMapping
    ResponseEntity<List<E>> getAll();

    @GetMapping("{id}")
    ResponseEntity<E> findById(@PathVariable(name = "id") Long id);

    @PutMapping("{id}")
    ResponseEntity<E> update(@PathVariable(name = "id") Long id, @RequestBody E entity);

    @DeleteMapping("{id}")
    ResponseEntity delete(@PathVariable(name = "id") Long id);
}
