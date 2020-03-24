package com.treetrunk.trek.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Table(name = "ports")
public class Port extends AbstractEntity {

    @Column(name = "number")
    private int number;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "end_point")
    private String endPoint;

    @Column(name = "transit_port_id")
    private Long transitPortId;

    @Column(name = "cross_port_id")
    private Long crossPortId;

    @Column(name = "service")
    private String service;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "module_id")
    private Module module;

    public void setModule(Module module) {
        this.module = module;
    }
}

