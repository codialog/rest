package com.treetrunk.trek.controller;

import com.treetrunk.trek.model.Module;
import com.treetrunk.trek.service.impl.ModuleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/module")
public class ModuleController {

    private final ModuleServiceImpl moduleService;

    public ModuleController(ModuleServiceImpl moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping
    public List<Module> getAll() {
        return moduleService.getAll();
    }

    @GetMapping("{id}")
    private ResponseEntity<Module> findById(@PathVariable(name = "id") Long id) {
        Module module = moduleService.findById(id);
        if (module == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(module, HttpStatus.OK);
    }

    @PostMapping
    public Module create(@RequestBody Module module) {
        module.setCreatedDate(LocalDateTime.now());
        return moduleService.create(module);
    }

    @PutMapping("{id}")
    public Module update(@PathVariable(name = "id") Long id,
                         @RequestBody Module module) {
        module.setUpdatedDate(LocalDateTime.now());
        Module updateModule = moduleService.findById(id);
        return moduleService.update(updateModule, module);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        moduleService.delete(id);
    }
}
