package com.treetrunk.trek.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "servers")
public class Server extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;
}

