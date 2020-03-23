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
}
