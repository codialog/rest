package com.treetrunk.trek.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.logging.Logger;

@MappedSuperclass
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public abstract class AbstractEntity implements Serializable {

    @JsonView(Views.Common.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    @PreUpdate
    @PreRemove
    private void preUpdateFunction(){
        Logger logger = Logger.getLogger("Class AbstractEntity");
        logger.info("Inside preUpdateFunction ....");
        assert this.getId() != null;
    }
}
