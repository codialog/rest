package com.treetrunk.trek.service;

import com.treetrunk.trek.model.Server;
import com.treetrunk.trek.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerService extends AbstractService<Server, ServerRepository> {

    @Autowired
    public ServerService(ServerRepository repository) {
        super(repository);
    }

    @Override
    public Server create(Server server) {
        return super.create(server);
    }

    @Override
    public Server update(Long id, Server customServer) {
        String[] ignoreProperties = new String[]{"id", "crosses"};
        return super.update(id, customServer, ignoreProperties);
    }

    public Server findByName(String name) {
        return repository.findByName(name);
    }
}
