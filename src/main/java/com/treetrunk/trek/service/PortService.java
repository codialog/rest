package com.treetrunk.trek.service;

import com.treetrunk.trek.model.Port;

import java.util.List;

public interface PortService {

    Port create(Port port);

    Port update(Port updatePort, Port port);

    void delete(Long id);

    List<Port> getAll();

    Port findById(Long id);
}
