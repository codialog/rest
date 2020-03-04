package com.treetrunk.trek.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "modules")
public class Module extends BaseEntity {

    @Column(name = "number")
    private int number;

    @Column(name = "amount_slots")
    private int amountSlots;

    @Column(name = "empty_slots")
    private int emptySlots;

    @Column(name = "cross_id")
    private Long crossId;
}
