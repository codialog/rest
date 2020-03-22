package com.treetrunk.trek.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "modules")
public class Module extends BaseEntity {

    @Column(name = "number")
    private int number;

    @Column(name = "amount_slots")
    private int amountSlots;

    @Column(name = "empty_slots")
    private int emptySlots;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "cross_id")
    private Cross cross;

    @OneToMany(mappedBy = "module", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("module")
    private Set<Port> ports;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAmountSlots() {
        return amountSlots;
    }

    public void setAmountSlots(int amountSlots) {
        this.amountSlots = amountSlots;
    }

    public int getEmptySlots() {
        return emptySlots;
    }

    public void setEmptySlots(int emptySlots) {
        this.emptySlots = emptySlots;
    }

    public Cross getCross() {
        return cross;
    }

    public void setCross(Cross cross) {
        this.cross = cross;
    }

    public Set<Port> getPorts() {
        return ports;
    }

    public void setPorts(Set<Port> ports) {
        this.ports = ports;
    }
}
