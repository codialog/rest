package com.treetrunk.trek.repository;

import com.treetrunk.trek.model.Module;
import com.treetrunk.trek.model.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Query("SELECT m FROM Module m where m.crossId = :crossId")
    public List<Module> findByCrossId(@Param("crossId") Long crossId);
}
