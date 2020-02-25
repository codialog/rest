package com.treetrunk.trek.controller;

import com.treetrunk.trek.model.Cross;
import com.treetrunk.trek.service.impl.CrossServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/cross")
public class CrossController {

    private final CrossServiceImpl portServiceImpl;

    public CrossController(CrossServiceImpl portServiceImpl) {
        this.portServiceImpl = portServiceImpl;
    }

    @GetMapping
    public List<Cross> getAll() {
        return portServiceImpl.getAll();
    }

    @GetMapping("{id}")
    private ResponseEntity<Cross> findById(@PathVariable(name = "id") Long id) {
        Cross cross = portServiceImpl.findById(id);
        if (cross == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cross, HttpStatus.OK);
    }

    @PostMapping
    public Cross create(@RequestBody Cross cross) {
        cross.setCreated(new Date());
        return portServiceImpl.create(cross);
    }

    @PutMapping("{id}")
    public Cross update(@PathVariable(name = "id") Long id,
                        @RequestBody Cross cross) {
        cross.setUpdated(new Date());
        Cross updateCross = portServiceImpl.findById(id);
        return portServiceImpl.update(updateCross, cross);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        portServiceImpl.delete(id);
    }

}
