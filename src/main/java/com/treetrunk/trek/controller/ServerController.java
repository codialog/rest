package com.treetrunk.trek.controller;

import com.treetrunk.trek.model.Server;
import com.treetrunk.trek.service.ServerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/server")
public class ServerController extends AbstractController<Server, ServerService> {

    protected ServerController(ServerService service) {
        super(service);
    }
}
