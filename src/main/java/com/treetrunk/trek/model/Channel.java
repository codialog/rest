package com.treetrunk.trek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "channels")
public class Channel extends BaseEntity {

    @Column(name = "direction")
    private String direction;

    @Column(name = "amount_ports")
    private Integer amountPorts;

    @Column(name = "free_ports")
    private Integer freePorts;

    @Column(name = "cross_id")
    private Long crossId;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getAmountPorts() {
        return amountPorts;
    }

    public void setAmountPorts(Integer amountPorts) {
        this.amountPorts = amountPorts;
    }

    public Integer getFreePorts() {
        return freePorts;
    }

    public void setFreePorts(Integer freePorts) {
        this.freePorts = freePorts;
    }

    public Long getCrossId() {
        return crossId;
    }

    public void setCrossId(Long crossId) {
        this.crossId = crossId;
    }
}

