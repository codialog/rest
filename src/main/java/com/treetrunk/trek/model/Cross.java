package com.treetrunk.trek.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "crosses")
public class Cross extends AbstractEntity {

    @CreatedDate
    @Column(name = "created", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "updated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;

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
    @JsonIgnoreProperties("crosses")
    private Server server;

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

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

