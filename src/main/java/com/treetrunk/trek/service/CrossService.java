package com.treetrunk.trek.service;

import com.treetrunk.trek.model.Cross;
import com.treetrunk.trek.repository.CrossRepository;
import org.springframework.stereotype.Service;

@Service
public class CrossService extends AbstractService<Cross, CrossRepository> {

    public CrossService(CrossRepository repository) {
        super(repository);
    }
}
