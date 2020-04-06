package com.treetrunk.trek.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column(name = "number")
    private int number;

    @Column(name = "amount_port_slots")
    private int amountPortSlots;

    @Column(name = "empty_port_slots")
    private int emptyPortSlots;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "cross_id")
    private Cross cross;

    @OneToMany(mappedBy = "module", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("module")
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
