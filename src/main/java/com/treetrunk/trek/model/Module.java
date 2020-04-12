package com.treetrunk.trek.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Table(name = "modules")
public class Module extends AbstractEntity {

    @JsonView(Views.Common.class)
    @Column(name = "number")
    private int number;

    @JsonView(Views.Common.class)
    @Column(name = "amount_port_slots")
    private int amountPortSlots;

    @JsonView(Views.Common.class)
    @Column(name = "empty_port_slots")
    private int emptyPortSlots;

    @JoinColumn(name = "cross_id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Cross cross;

    @JsonView(Views.Cross.class)
    @OneToMany(mappedBy = "module", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Port> ports;

    public void setCross(Cross cross) {
        this.cross = cross;
    }

    public void setPorts(Set<Port> ports) {
        this.ports = ports;
        for (Port port : this.ports) {
            port.setModule(this);
        }
    }
}
