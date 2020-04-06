package com.treetrunk.trek.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    @UpdateTimestamp
    @Column(name = "updated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updated;

    @Column(name = "name")
    private String name;

    @Column(name = "count_module_slots")
    private Integer amountModuleSlots;

    @Column(name = "empty_module_slots")
    private Integer emptyModuleSlots;

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

