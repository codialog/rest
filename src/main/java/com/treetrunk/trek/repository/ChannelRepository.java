package com.treetrunk.trek.repository;
import com.treetrunk.trek.model.Channel;
import com.treetrunk.trek.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    @Query("SELECT ch FROM Channel ch where ch.crossId = :crossId")
    public List<Channel> findByCrossId(@Param("crossId") Long crossId);
}
