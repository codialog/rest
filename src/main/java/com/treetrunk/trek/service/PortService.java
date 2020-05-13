package com.treetrunk.trek.service;

import com.treetrunk.trek.model.Port;
import com.treetrunk.trek.repository.PortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortService extends AbstractService<Port, PortRepository> {

    @Autowired
    public PortService(PortRepository repository) {
        super(repository);
    }
}
