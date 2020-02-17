package com.treetrunk.trek.controller.port;

import com.treetrunk.trek.model.port.Port;
import com.treetrunk.trek.service.impl.PortServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/port")
public class PortController {

    private final PortServiceImpl portServiceImpl;

    public PortController(PortServiceImpl portServiceImpl) {
        this.portServiceImpl = portServiceImpl;
    }

    @GetMapping
    public List<Port> getAll() {
        List<Port> portList = portServiceImpl.getAll();
        return portList;
    }

    @GetMapping("{id}")
    private ResponseEntity<Port> findById(@PathVariable(name = "id") Long id) {
        Port port = portServiceImpl.findById(id);
        if (port == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(port, HttpStatus.OK);
    }

    @PostMapping
    public Port create(@RequestBody Port port) {
        port.setCreated(new Date());
        return portServiceImpl.create(port);
    }

    @PutMapping("{id}")
    public Port update(@PathVariable(name = "id") Long id,
                       @RequestBody Port port) {
        port.setUpdated(new Date());
        Port updatePort = portServiceImpl.findById(id);
        return portServiceImpl.update(updatePort, port);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        portServiceImpl.delete(id);
    }

}
