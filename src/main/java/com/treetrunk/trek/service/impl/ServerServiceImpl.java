package com.treetrunk.trek.service.impl;

import com.treetrunk.trek.model.Server;
import com.treetrunk.trek.repository.ServerRepository;
import com.treetrunk.trek.service.ServerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerServiceImpl implements ServerService {
    private final ServerRepository serverRepository;

    @Autowired
    public ServerServiceImpl(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public Server create(Server server) {
        return serverRepository.save(server);
    }

    @Override
    public Server update(Server updateServer, Server server) {
        BeanUtils.copyProperties(server, updateServer, "id", "created");
        return serverRepository.save(updateServer);
    }

    @Override
    public void delete(Long id) {
        serverRepository.deleteById(id);
    }

    @Override
    public List<Server> getAll() {
        return serverRepository.findAll();
    }

    @Override
    public Server findById(Long id) {
        return serverRepository.findById(id).orElse(null);
    }


}
