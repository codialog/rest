package com.treetrunk.trek.service;

import com.treetrunk.trek.model.Cross;
import com.treetrunk.trek.model.Module;
import com.treetrunk.trek.model.Port;
import com.treetrunk.trek.repository.CrossRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrossService extends AbstractService<Cross, CrossRepository> {

    private final ModuleService moduleService;
    private final PortService portService;

    @Autowired
    public CrossService(CrossRepository repository, ModuleService moduleService, PortService portService) {
        super(repository);
        this.moduleService = moduleService;
        this.portService = portService;
    }

    @Override
    public Cross update(Long id, Cross customCross) {
        Cross cross = repository.getOne(id);
        List<Long> modulesId = cross.getModulesId();
        List<Long> customModulesId = customCross.getModulesId();
        for (Long moduleId : modulesId) {
            if (!customModulesId.contains(moduleId)) {
                moduleService.delete(moduleId);
                cross.getModule(moduleId);
            }
        }
        List<Long> portsId = new ArrayList<>();
        List<Long> customPortsId = new ArrayList<>();
        for (Module module : cross.getModules()) {
            portsId = module.getPortsId();
            customPortsId = customCross.getModule(module.getId()).getPortsId();
            for (Long portId : portsId) {
                if (!customPortsId.contains(portId)) {
                    Port port = module.getPort(portId);
                    Port crossPort = port.getCrossPort();
                    if (crossPort != null) {
                        crossPort.deleteLinks();
                        portService.update(crossPort.getId(), crossPort);
                    }
                    portService.delete(portId);
                }
            }
        }
        return super.update(id, customCross);
    }
}
