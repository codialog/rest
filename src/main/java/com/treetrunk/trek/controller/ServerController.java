package com.treetrunk.trek.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.treetrunk.trek.model.Cross;
import com.treetrunk.trek.model.Views;
import com.treetrunk.trek.model.Server;
import com.treetrunk.trek.service.ServerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/server")
public class ServerController extends AbstractController<Server, ServerService> {

    protected ServerController(ServerService service) {
        super(service);
    }

    @JsonView(Views.Server.class)
    @Override
    public ResponseEntity<Server> findById(@PathVariable(name = "id") Long id) {
        return super.findById(id);
    }

    @JsonView(Views.Server.class)
    @Override
    public ResponseEntity<List<Server>> getAll() {
        return super.getAll();
    }
}
