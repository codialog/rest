package com.treetrunk.trek.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.*;

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
    @Column(name = "amount_module_slots")
    private Integer amountModuleSlots;

    @JsonView(Views.Cross.class)
    @OneToMany(mappedBy = "cross",
            fetch = FetchType.EAGER,
            cascade={CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Module> modules;

    @JsonView(Views.Cross.class)
    @JoinColumn(name = "server_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Server server;

    public void setModules(Set<Module> modules) {
        this.modules = modules;
        for (Module module : this.modules) {
            module.setCross(this);
        }
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public String getName() {
        return name;
    }

    public Integer getAmountModuleSlots() {
        return amountModuleSlots;
    }

    public int getEmptyModuleSlots() {
        return this.amountModuleSlots - this.modules.size();
    }

    public Set<Module> getModules() {
        return modules;
    }

    public Server getServer() {
        return server;
    }

    public List<Long> getModulesId() {
        List<Long> list = new ArrayList<>();
        this.modules.forEach(m->list.add(m.getId()));
        return list;
    }

    public Module getModule(Long id){
        return getModules().stream().filter(module -> module.getId().equals(id)).findFirst().orElse(null);
    }

    public void deleteModule(Long id) {
        Iterator<Module> moduleIterator = getModules().iterator();
        while(moduleIterator.hasNext()) {
            Module nextModule = moduleIterator.next();
            if (nextModule.getId().equals(id)) {
                moduleIterator.remove();
                break;
            }
        }
    }
}
