package com.treetrunk.trek.model.module;

import com.treetrunk.trek.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "modules")
public class Module extends BaseEntity {

    @Column(name = "amount_slots")
    private int amount_slots;

    @Column(name = "empty_slots")
    private int empty_slots;
}
