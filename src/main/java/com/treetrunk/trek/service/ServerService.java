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
        if (findByName(server.getName()) == null) {
            return super.create(server);
        } else return null;
    }

    @Override
    public Server update(Server server, Server customServer) {
        if (!customServer.getName().equals(server.getName()) && findByName(customServer.getName()) != null) {
            return null;
        }
        String[] ignoreProperties = new String[]{"id", "crosses"};
        return super.update(server, customServer, ignoreProperties);
    }

    public Server findByName(String name) {
        return repository.findByName(name);
    }
}
