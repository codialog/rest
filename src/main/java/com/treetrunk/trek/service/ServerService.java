package com.treetrunk.trek.service;

import com.treetrunk.trek.model.Server;
import com.treetrunk.trek.repository.ServerRepository;
import org.springframework.stereotype.Service;

@Service
public class ServerService extends AbstractService<Server, ServerRepository> {

    public ServerService(ServerRepository repository) {
        super(repository);
    }
}
