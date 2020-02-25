package com.treetrunk.trek.controller;

import com.treetrunk.trek.model.Module;
import com.treetrunk.trek.service.impl.ModuleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/module")
public class ModuleController {

    private final ModuleServiceImpl moduleServiceImpl;

    public ModuleController(ModuleServiceImpl moduleServiceImpl) {
        this.moduleServiceImpl = moduleServiceImpl;
    }

    @GetMapping
    public List<Module> getAll() {
        return moduleServiceImpl.getAll();
    }

    @GetMapping("{id}")
    private ResponseEntity<Module> findById(@PathVariable(name = "id") Long id) {
        Module module = moduleServiceImpl.findById(id);
        if (module == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(module, HttpStatus.OK);
    }

    @PostMapping
    public Module create(@RequestBody Module module) {
        module.setCreated(new Date());
        return moduleServiceImpl.create(module);
    }

    @PutMapping("{id}")
    public Module update(@PathVariable(name = "id") Long id,
                         @RequestBody Module module) {
        module.setUpdated(new Date());
        Module updateModule = moduleServiceImpl.findById(id);
        return moduleServiceImpl.update(updateModule, module);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        moduleServiceImpl.delete(id);
    }
}
