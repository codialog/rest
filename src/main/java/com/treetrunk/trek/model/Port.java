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
    private int number;

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

    @JoinColumn(name = "module_id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Module module;

    public void setModule(Module module) {
        this.module = module;
    }

    public int getNumber() {
        return number;
    }

    public Module getModule() {
        return module;
    }
}
