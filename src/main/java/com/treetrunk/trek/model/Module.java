package com.treetrunk.trek.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Table(name = "modules")
public class Module extends AbstractEntity {

    @JsonView(Views.Common.class)
    @Column(name = "number")
    private Integer number;

    @JsonView(Views.Common.class)
    @Column(name = "amount_port_slots")
    private Integer amountPortSlots;

    @JsonView(Views.Cross.class)
    @OneToMany(mappedBy = "module",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Port> ports;

    @JoinColumn(name = "cross_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Cross cross;

    public Module() {
    }

    public Module(Integer number,
                  Integer amountPortSlots,
                  Set<Port> ports,
                  Cross cross) {
        this.number = number;
        this.amountPortSlots = amountPortSlots;
        this.ports = ports;
        this.cross = cross;

    }

    public void setCross(Cross cross) {
        this.cross = cross;
    }

    public void setPorts(Set<Port> ports) {
        this.ports = ports;
        for (Port port : this.ports) {
            port.setModule(this);
        }
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getAmountPortSlots() {
        return amountPortSlots;
    }

    public Cross getCross() {
        return cross;
    }

    public Set<Port> getPorts() {
        return ports;
    }

    public Integer getEmptyPortSlots() {
        return this.amountPortSlots - this.ports.size();
    }

    public List<Long> getPortsId() {
        List<Long> list = new ArrayList<>();
        this.ports
                .stream()
                .filter(p -> p.getId() != null)
                .forEach(p -> list.add(p.getId()));
        return list;
    }

    public Port getPort(Long id) {
        return getPorts().stream().filter(port -> port.getId().equals(id)).findFirst().orElse(null);
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setAmountPortSlots(Integer amountPortSlots) {
        this.amountPortSlots = amountPortSlots;
    }

    public void addPort(Port port) {
        Set<Port> ports = getPorts();
        port.setModule(this);
        ports.add(port);
    }
}
