package com.treetrunk.trek.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.treetrunk.trek.model.Server;
import com.treetrunk.trek.model.Views;
import com.treetrunk.trek.service.ServerService;
import com.treetrunk.trek.validators.ServersValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/server")
public class ServerController extends AbstractController<Server, ServerService> {

    private final ServersValidator serversValidator;

    @Autowired
    public ServerController(ServerService service, ServersValidator serversValidator) {
        super(service);
        this.serversValidator = serversValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(serversValidator);
    }

    @JsonView(Views.Server.class)
    @Override
    public ResponseEntity<List<Server>> getAll() {
        return super.getAll();
    }

    @JsonView(Views.Server.class)
    @Override
    public ResponseEntity<Server> findById(@PathVariable(name = "id") Long id) {
        return super.findById(id);
    }

    @JsonView(Views.Server.class)
    @Override
    public ResponseEntity<Server> create(@RequestBody @Valid Server server) {
        return super.create(server);
    }

    @JsonView(Views.Server.class)
    @Override
    public ResponseEntity<Server> update(@PathVariable(name = "id") Long id,
                                         @Valid @RequestBody Server customServer) {
        return super.update(id, customServer);
    }
}
