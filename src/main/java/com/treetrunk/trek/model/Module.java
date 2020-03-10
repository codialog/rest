package com.treetrunk.trek.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

import com.treetrunk.trek.model.Port;
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

    public Long getCrossId() {
        return crossId;
    }

    public void setCrossId(Long crossId) {
        this.crossId = crossId;
    }
}
