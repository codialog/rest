package com.treetrunk.trek.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "crosses")
public class Cross extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "count_modules")
    private Integer amountPorts;

    @Column(name = "empty_modules")
    private Integer emptyPorts;

    @OneToMany(mappedBy = "cross", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("cross")
    private Set<Module> modules;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "server_id")
    @JsonIgnoreProperties("server")
    private Server server;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountPorts() {
        return amountPorts;
    }

    public void setAmountPorts(Integer amountPorts) {
        this.amountPorts = amountPorts;
    }

    public Integer getEmptyPorts() {
        return emptyPorts;
    }

    public void setEmptyPorts(Integer emptyPorts) {
        this.emptyPorts = emptyPorts;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}

