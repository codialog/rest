package com.treetrunk.trek.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "crosses")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Cross extends AbstractEntity {

    @JsonView(Views.Common.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private Date created;

    @JsonView(Views.Common.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    @Column(name = "updated")
    private Date updated;

    @JsonView(Views.Common.class)
    @Column(name = "name")
    private String name;

    @JsonView(Views.Common.class)
    @Column(name = "count_module_slots")
    private Integer amountModuleSlots;

    @JsonView(Views.Common.class)
    @Column(name = "empty_module_slots")
    private Integer emptyModuleSlots;

    @JsonView(Views.Cross.class)
    @OneToMany(mappedBy = "cross", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Module> modules;

    @JsonView(Views.Cross.class)
    @JoinColumn(name = "server_id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Server server;

    public void setModules(Set<Module> modules) {
        this.modules = modules;
        for (Module module : this.modules) {
            module.setCross(this);
        }
    }


}

