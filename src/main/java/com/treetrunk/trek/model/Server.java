package com.treetrunk.trek.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "servers")
public class Server extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "server", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("server")
    private Set<Cross> crosses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Cross> getCrosses() {
        return crosses;
    }

    public void setCrosses(Set<Cross> crosses) {
        this.crosses = crosses;
    }
}

