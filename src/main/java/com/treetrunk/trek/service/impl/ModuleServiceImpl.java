package com.treetrunk.trek.service.impl;

import com.treetrunk.trek.model.module.Module;
import com.treetrunk.trek.repository.ModuleRepository;
import com.treetrunk.trek.service.ModuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    private final ModuleRepository moduleRepository;

    @Autowired
    public ModuleServiceImpl(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    @Override
    public Module create(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public Module update(Module updateModule, Module module) {
        BeanUtils.copyProperties(module, updateModule, "id", "created");
        return moduleRepository.save(updateModule);
    }

    @Override
    public void delete(Long id) {
        moduleRepository.deleteById(id);
    }

    @Override
    public List<Module> getAll() {
        return moduleRepository.findAll();
    }

    @Override
    public Module findById(Long id) {
        return moduleRepository.findById(id).orElse(null);
    }
}
