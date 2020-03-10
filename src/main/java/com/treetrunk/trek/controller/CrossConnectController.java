package com.treetrunk.trek.controller;


import com.treetrunk.trek.model.Connection;
import com.treetrunk.trek.model.Module;
import com.treetrunk.trek.model.Port;
import com.treetrunk.trek.service.impl.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/connection")
public class CrossConnectController {

    private final ServerServiceImpl serverService;
    private final ChannelServiceImpl channelService;
    private final ModuleServiceImpl moduleService;
    private final PortServiceImpl portService;
    private final CrossServiceImpl crossService;

    public CrossConnectController(ServerServiceImpl serverService,
                                  ChannelServiceImpl channelService,
                                  ModuleServiceImpl moduleService,
                                  PortServiceImpl portService,
                                  CrossServiceImpl crossService) {
        this.serverService = serverService;
        this.channelService = channelService;
        this.moduleService = moduleService;
        this.portService = portService;
        this.crossService = crossService;
    }

    @GetMapping("{id}")
    private Connection findById(@PathVariable(name = "id") Long id) {
        Connection connection = new Connection();
        connection.setCross(crossService.findById(id));
        connection.setModules(moduleService.findByCrossId(id));
        connection.setChannels(channelService.findByCrossId(id));
        Map<Long, List<Port>> ports = new HashMap<>();
        for (Module module : connection.getModules()) {
            ports.put(module.getId(), portService.findByModuleId(module.getId()));
        }
        connection.setPorts(ports);
        return connection;
    }
}
