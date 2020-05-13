package com.treetrunk.trek.service;

import com.treetrunk.trek.model.Module;
import com.treetrunk.trek.model.Port;
import com.treetrunk.trek.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService extends AbstractService<Module, ModuleRepository> {

    private final PortService portService;

    @Autowired
    public ModuleService(ModuleRepository repository, PortService portService) {
        super(repository);
        this.portService = portService;
    }

    @Override
    public void delete(Long id) {
        Module module = super.findById(id);
        for (Port port : module.getPorts()) {
            Port crossPort = port.getCrossPort();
            if (crossPort != null) {
                crossPort.deleteLinks();
                portService.update(crossPort.getId(), crossPort);
            }
        }
        super.delete(id);
    }
}
