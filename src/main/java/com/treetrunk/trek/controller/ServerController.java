package com.treetrunk.trek.controller;

import com.treetrunk.trek.model.Server;
import com.treetrunk.trek.service.impl.ServerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/server")
public class ServerController {

    private final ServerServiceImpl serverService;

    public ServerController(ServerServiceImpl serverService) {
        this.serverService = serverService;
    }

    @GetMapping
    public List<Server> getAll() {
        return serverService.getAll();
    }

    @GetMapping("{id}")
    private ResponseEntity<Server> findById(@PathVariable(name = "id") Long id) {
        Server server = serverService.findById(id);
        if (server == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(server, HttpStatus.OK);
    }

    @PostMapping
    public Server create(@RequestBody Server server) {
        server.setCreatedDate(LocalDateTime.now());
        return serverService.create(server);
    }

    @PutMapping("{id}")
    public Server update(@PathVariable(name = "id") Long id,
                         @RequestBody Server server) {
        server.setUpdatedDate(LocalDateTime.now());
        Server updateServer = serverService.findById(id);
        return serverService.update(updateServer, server);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        serverService.delete(id);
    }

}
