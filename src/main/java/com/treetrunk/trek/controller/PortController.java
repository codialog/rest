package com.treetrunk.trek.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.treetrunk.trek.model.Port;
import com.treetrunk.trek.model.Views;
import com.treetrunk.trek.service.PortService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/port")
public class PortController extends AbstractController<Port, PortService> {
    protected PortController(PortService service) {
        super(service);
    }

    @JsonView(Views.Cross.class)
    @Override
    public ResponseEntity<List<Port>> getAll() {
        return super.getAll();
    }

    @JsonView(Views.Cross.class)
    @Override
    public ResponseEntity<Port> findById(@PathVariable(name = "id") Long id) {
        return super.findById(id);
    }

    @JsonView(Views.Cross.class)
    @Override
    public ResponseEntity<Port> create(@RequestBody Port port) {
        return super.create(port);
    }


}
