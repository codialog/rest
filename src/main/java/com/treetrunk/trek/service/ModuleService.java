package com.treetrunk.trek.service;

import com.treetrunk.trek.model.Module;
import com.treetrunk.trek.repository.ModuleRepository;
import org.springframework.stereotype.Service;

@Service
public class ModuleService extends AbstractService<Module, ModuleRepository> {

    public ModuleService(ModuleRepository repository) {
        super(repository);
    }
}
