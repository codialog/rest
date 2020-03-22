package com.treetrunk.trek.service;

import com.treetrunk.trek.model.Module;
import com.treetrunk.trek.model.Port;

import java.util.List;

public interface ModuleService {

    Module create(Module module, List<Port> ports);

    Module update(Module updateModule, Module module);

    void delete(Long id);

    List<Module> getAll();

    Module findById(Long id);

}
