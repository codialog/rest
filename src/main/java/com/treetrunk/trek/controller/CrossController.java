package com.treetrunk.trek.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.treetrunk.trek.model.Cross;
import com.treetrunk.trek.model.Views;
import com.treetrunk.trek.service.CrossService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/cross")

public class CrossController extends AbstractController<Cross, CrossService> {

    public CrossController(CrossService service) {
        super(service);
    }

    @JsonView(Views.Cross.class)
    @Override
    public ResponseEntity<Cross> findById(@PathVariable(name = "id") Long id) {
        return super.findById(id);
    }

    @JsonView(Views.Cross.class)
    @Override
    public ResponseEntity<List<Cross>> getAll() {
        return super.getAll();
    }

    @JsonView(Views.Cross.class)
    @Override
    public ResponseEntity<Cross> create(@RequestBody Cross cross) {
        return super.create(cross);
    }
}
