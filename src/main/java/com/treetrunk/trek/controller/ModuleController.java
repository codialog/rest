package com.treetrunk.trek.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.treetrunk.trek.model.Module;
import com.treetrunk.trek.model.Views;
import com.treetrunk.trek.service.ModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/module")
public class ModuleController extends AbstractController<Module, ModuleService> {
    protected ModuleController(ModuleService service) {
        super(service);
    }

    @JsonView(Views.Cross.class)
    @Override
    public ResponseEntity<List<Module>> getAll() {
        return super.getAll();
    }

    @JsonView(Views.Cross.class)
    @Override
    public ResponseEntity<Module> findById(@PathVariable(name = "id") Long id) {
        return super.findById(id);
    }

    @JsonView(Views.Cross.class)
    @Override
    public ResponseEntity<Module> create(@RequestBody Module module) {
        return super.create(module);
    }


}
