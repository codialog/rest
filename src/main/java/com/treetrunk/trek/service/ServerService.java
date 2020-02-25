package com.treetrunk.trek.service;

import com.treetrunk.trek.model.Server;

import java.util.List;

public interface ServerService {

    Server create(Server server);

    Server update(Server updateServer, Server server);

    void delete(Long id);

    List<Server> getAll();

    Server findById(Long id);
}
