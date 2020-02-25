package com.treetrunk.trek.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ports")
public class Port extends BaseEntity {

    @Column(name = "number")
    private int number;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "prev_port_id")
    private Long prevPortId;

    @Column(name = "next_port_id")
    private Long nextPortId;

    @Column(name = "end_point")
    private String endPoint;

    @Column(name = "service")
    private String service;

    @Column(name = "comment")
    private String comment;
}

