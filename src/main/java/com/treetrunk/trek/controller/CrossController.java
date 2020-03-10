package com.treetrunk.trek.controller;

import com.treetrunk.trek.model.Cross;
import com.treetrunk.trek.service.impl.CrossServiceImpl;
import com.treetrunk.trek.service.impl.PortServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/cross")
public class CrossController {

    private final CrossServiceImpl crossService;
    private final PortServiceImpl portService;

    public CrossController(CrossServiceImpl crossService, PortServiceImpl portService) {
        this.crossService = crossService;
        this.portService = portService;
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
        } else {

        }
        return new ResponseEntity<>(cross, HttpStatus.OK);
    }

    @PostMapping
    public Cross create(@RequestBody Cross cross) {
        cross.setCreatedDate(LocalDateTime.now());
        return crossService.create(cross);
    }

    @PutMapping("{id}")
    public Cross update(@PathVariable(name = "id") Long id,
                        @RequestBody Cross cross) {
        cross.setUpdatedDate(LocalDateTime.now());
        Cross updateCross = crossService.findById(id);
        return crossService.update(updateCross, cross);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        crossService.delete(id);
    }

}
