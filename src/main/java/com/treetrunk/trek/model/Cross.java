package com.treetrunk.trek.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "crosses")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Cross extends AbstractEntity {

    @CreatedDate
    @Column(name = "created", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    @LastModifiedDate
    @Column(name = "updated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updated;

    @Column(name = "name")
    private String name;

    @Column(name = "count_modules")
    private Integer amountSlots;

    @Column(name = "empty_modules")
    private Integer emptySlots;

    @OneToMany(mappedBy = "cross", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cross")
    private Set<Module> modules;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "server_id")
    @JsonIgnoreProperties("crosses")
    private Server server;

    public void setModules(Set<Module> modules) {
        this.modules = modules;
        for (Module module: this.modules){
            module.setCross(this);
        }
    }
}

