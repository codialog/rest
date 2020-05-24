package com.treetrunk.trek.repository;

import com.treetrunk.trek.model.Port;
import com.treetrunk.trek.model.Server;
import org.springframework.stereotype.Repository;

@Repository
public interface PortRepository extends CommonRepository<Port> {

    Server findByNumber(int number);
}
