package com.treetrunk.trek.event;

import com.treetrunk.trek.model.Cross;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CrossEventListener extends AbstractRepositoryEventListener<Cross> {

    Logger logger = Logger.getLogger("Class BeforeSaveEventListener");

    @Override
    public void onBeforeSave(Cross cross) {
        logger.info("Inside  onBeforeSave ....");
    }

    @Override
    public void onAfterDelete(Cross cross) {
        logger.info("Inside  onAfterDelete ....");
    }


}
