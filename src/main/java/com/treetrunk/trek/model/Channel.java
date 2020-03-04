package com.treetrunk.trek.model;

import lombok.Data;

import javax.persistence.*;

@Data
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
}

