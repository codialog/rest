package com.treetrunk.trek.validators;

import com.treetrunk.trek.model.Cross;
import com.treetrunk.trek.model.Module;
import com.treetrunk.trek.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class CrossValidator implements Validator {

    Logger logger = Logger.getLogger("Class CrossFormValidator");

    private final ServerService serverService;
    private final ModuleValidator moduleValidator;

    @Autowired
    public CrossValidator(ServerService serverService, ModuleValidator moduleValidator) {
        this.serverService = serverService;
        this.moduleValidator = moduleValidator;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Cross.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        logger.info("Inside create validate ....");
        Cross cross = (Cross) obj;
        validateCrossName(cross, errors);
        validateModules(cross, errors);
    }

    public void validateCrossName(Cross cross, Errors errors) {
        String name = cross.getName();
        // Empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Cross name is a required field");
        if (errors.getFieldError("name") != null) {
            return;
        }
        // Length
        int maxLengthName = 50;
        int minLengthName = 2;
        int crossNameLength = name.length();
        if (crossNameLength > maxLengthName) {
            errors.rejectValue("name", "Cross name should be shorter then " + maxLengthName + " characters");
            return;
        } else if (crossNameLength < minLengthName) {
            errors.rejectValue("name", "Cross name should be longer than " + minLengthName + " characters");
            return;
        }
        // Duplicate
        Long serverId = cross.getServer().getId();
        for (Cross duplicateCross : serverService.findById(serverId).getCrosses()) {
            if (name.equals(duplicateCross.getName())) {
                errors.rejectValue("name", "Cross name in current server is already in use");
                return;
            }
        }
    }

    public void validateModules(Cross cross, Errors errors) {
        //Slots
        if (cross.getAmountModuleSlots() <= 0) {
            errors.rejectValue("amountModuleSlots",
                    "Amount slots of modules should be more then 0");
        } else if (cross.getEmptyModuleSlots() < 0) {
            errors.rejectValue("amountModuleSlots",
                    "Amount slots of modules more then limit: " + cross.getAmountModuleSlots());
        }
        //Duplicate
        Collection<Module> moduleCollection = cross.getModules();
        Set<Integer> moduleNumbers = new HashSet<>();
        moduleCollection.forEach(m -> moduleNumbers.add(m.getNumber()));
        if (moduleCollection.size() > moduleNumbers.size()) {
            errors.rejectValue("modules", "Numbers of modules are duplicated");
        }
        //Module fields
        for (Module module : cross.getModules()) {
            moduleValidator.validate(module, errors);
        }

    }
}
