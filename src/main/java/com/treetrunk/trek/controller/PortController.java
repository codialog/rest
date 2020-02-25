package com.treetrunk.trek.controller;

import com.treetrunk.trek.model.Port;
import com.treetrunk.trek.service.impl.PortServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/port")
public class PortController {

    private final PortServiceImpl portService;

    public PortController(PortServiceImpl portService) {
        this.portService = portService;
    }

    @GetMapping
    public List<Port> getAll() {
        return portService.getAll();
    }

    @GetMapping("{id}")
    private ResponseEntity<Port> findById(@PathVariable(name = "id") Long id) {
        Port port = portService.findById(id);
        if (port == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(port, HttpStatus.OK);
    }

    @PostMapping
    public Port create(@RequestBody Port port) {
        port.setCreated(new Date());
        return portService.create(port);
    }

    @PutMapping("{id}")
    public Port update(@PathVariable(name = "id") Long id,
                       @RequestBody Port port) {
        port.setUpdated(new Date());
        Port updatePort = portService.findById(id);
        return portService.update(updatePort, port);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        portService.delete(id);
    }

}
