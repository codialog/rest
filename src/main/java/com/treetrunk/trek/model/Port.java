package com.treetrunk.trek.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ports")
public class Port extends BaseEntity {

    @Column(name = "number")
    @JsonView(Views.Port.class)
    private int number;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @JsonView(Views.Port.class)
    private Status status;

    @Column(name = "end_point")
    @JsonView(Views.Port.class)
    private String endPoint;

    @Column(name = "transit_port_id")
    @JsonView(Views.Port.class)
    private Long transitPortId;

    @Column(name = "cross_port_id")
    @JsonView(Views.Port.class)
    private Long prevPortId;

    @Column(name = "service")
    @JsonView(Views.Port.class)
    private String service;

    @Column(name = "comment")
    @JsonView(Views.Port.class)
    private String comment;

    @Column(name = "module_id")
    private Long moduleId;

    @Column(name = "channel_id")
    private Long channelId;
}

