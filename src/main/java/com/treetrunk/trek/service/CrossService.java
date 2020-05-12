package com.treetrunk.trek.service;

import com.treetrunk.trek.model.Cross;
import com.treetrunk.trek.repository.CrossRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrossService extends AbstractService<Cross, CrossRepository> {

    private ModuleService moduleService;

    @Autowired
    public CrossService(CrossRepository repository, ModuleService moduleService) {
        super(repository);
        this.moduleService = moduleService;
    }

    @Override
    public Cross update(Long id, Cross customCross) {
        Cross cross = repository.getOne(id);
        List<Long> modulesId = cross.getModulesId();
        List<Long> customModulesId = customCross.getModulesId();
        for(Long moduleId : modulesId) {
            if (!customModulesId.contains(moduleId)) {
                moduleService.delete(moduleId);
            }
        }
        return super.update(id, customCross);
    }
}
