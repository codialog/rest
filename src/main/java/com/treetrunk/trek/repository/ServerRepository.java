package com.treetrunk.trek.repository;
import com.treetrunk.trek.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
}
