package com.treetrunk.trek.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "crosses")
public class Cross extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "count_modules")
    private Integer amountPorts;

    @Column(name = "empty_modules")
    private Integer emptyPorts;
}

