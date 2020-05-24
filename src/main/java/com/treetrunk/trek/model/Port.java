package com.treetrunk.trek.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Table(name = "ports")
public class Port extends AbstractEntity {

    @JsonView(Views.Common.class)
    @Column(name = "number")
    private Integer number;

    @JsonView(Views.Common.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @JsonView(Views.Common.class)
    @Column(name = "end_point")
    private String endPoint;

    @JsonView(Views.Common.class)
    @JoinColumn(name = "transit_port_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Port transitPort;

    @JsonView(Views.Common.class)
    @JoinColumn(name = "cross_port_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Port crossPort;

    @JsonView(Views.Common.class)
    @Column(name = "service")
    private String service;

    @JsonView(Views.Common.class)
    @Column(name = "comment")
    private String comment;

    @JoinColumn(name = "module_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Module module;

    public Port() {
    }

    public Port(Integer number,
                Status status,
                String endPoint,
                Port transitPort,
                Port crossPort,
                String service,
                String comment,
                Module module) {
        this.number = number;
        this.status = status;
        this.endPoint = endPoint;
        this.transitPort = transitPort;
        this.crossPort = crossPort;
        this.service = service;
        this.comment = comment;
        this.module = module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Integer getNumber() {
        return number;
    }

    public Module getModule() {
        return module;
    }

    public void setNumber(Integer number) {
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

    public Port getTransitPort() {
        return transitPort;
    }

    public void setTransitPort(Port transitPort) {
        this.transitPort = transitPort;
    }

    public Port getCrossPort() {
        return crossPort;
    }

    public void setCrossPort(Port crossPort) {
        this.crossPort = crossPort;
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

    public void deleteLinks() {
        setCrossPort(null);
    }
}
