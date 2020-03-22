package com.treetrunk.trek.model;

import javax.persistence.*;

@Entity
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

    //@ManyToOne(optional = false, cascade = CascadeType.ALL)
    //@JoinColumn(name = "channel_id")
    //private Channel channel;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public Long getTransitPortId() {
        return transitPortId;
    }

    public void setTransitPortId(Long transitPortId) {
        this.transitPortId = transitPortId;
    }

    public Long getCrossPortId() {
        return crossPortId;
    }

    public void setCrossPortId(Long crossPortId) {
        this.crossPortId = crossPortId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

}

