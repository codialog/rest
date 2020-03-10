package com.treetrunk.trek.repository;
import com.treetrunk.trek.model.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PortRepository extends JpaRepository<Port, Long> {

    @Query("SELECT p FROM Port p where p.moduleId = :moduleId")
    public List<Port> findByModuleId(@Param("moduleId") Long moduleId);
}
