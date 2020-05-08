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
@Table(name = "servers")
public class Server extends AbstractEntity {

    @JsonView(Views.Common.class)
    @Column(name = "name", unique = true)
    private String name;

    @JsonView(Views.Common.class)
    @Column(name = "address")
    private String address;

    @JsonView(Views.Server.class)
    @OneToMany(mappedBy = "server", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Cross> crosses;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Set<Cross> getCrosses() {
        return crosses;
    }
}
