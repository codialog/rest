package com.treetrunk.trek.service;

import com.treetrunk.trek.model.Server;
import com.treetrunk.trek.repository.ServerRepository;
import org.springframework.stereotype.Service;

@Service
public class ServerService extends AbstractService<Server, ServerRepository> {

    public ServerService(ServerRepository repository) {
        super(repository);
    }

    @Override
    public Server create(Server server) {
        if (super.findByName(server.getName()) == null) {
            return super.create(server);
        }
        return server;
    }
}
