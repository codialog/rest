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

    private final CrossServiceImpl crossService;

    public CrossController(CrossServiceImpl crossService) {
        this.crossService = crossService;
    }

    @GetMapping
    public List<Cross> getAll() {
        return crossService.getAll();
    }

    @GetMapping("{id}")
    private ResponseEntity<Cross> findById(@PathVariable(name = "id") Long id) {
        Cross cross = crossService.findById(id);
        if (cross == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cross, HttpStatus.OK);
    }

    @PostMapping
    public Cross create(@RequestBody Cross cross) {
        cross.setCreated(new Date());
        return crossService.create(cross);
    }

    @PutMapping("{id}")
    public Cross update(@PathVariable(name = "id") Long id,
                        @RequestBody Cross cross) {
        cross.setUpdated(new Date());
        Cross updateCross = crossService.findById(id);
        return crossService.update(updateCross, cross);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        crossService.delete(id);
    }

}
