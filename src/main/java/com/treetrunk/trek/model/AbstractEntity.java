package com.treetrunk.trek.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public abstract class AbstractEntity implements Serializable {

    @JsonView(Views.Common.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;
}
