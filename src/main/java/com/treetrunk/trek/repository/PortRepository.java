package com.treetrunk.trek.repository;
import com.treetrunk.trek.model.port.Port;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortRepository extends JpaRepository<Port, Long> {
}
