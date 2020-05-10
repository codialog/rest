package com.treetrunk.trek.event;

import com.treetrunk.trek.model.Cross;
import org.springframework.context.ApplicationEvent;

public class UpdateCrossEvent extends ApplicationEvent {

    private Long crossId;

    public UpdateCrossEvent(Cross customCross, Long crossId) {
        super(customCross);
        this.crossId = crossId;
    }
}