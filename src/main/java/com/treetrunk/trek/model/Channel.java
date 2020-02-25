package com.treetrunk.trek.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "channel")
public class Channel extends BaseEntity {

    @Column(name = "direction")
    private String direction;

    @Column(name = "amount_ports")
    private Integer amountPorts;

    @Column(name = "empty_ports")
    private Integer emptyPorts;
}

