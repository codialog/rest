package com.treetrunk.trek.validator;

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

    Logger logger = Logger.getLogger("Class CrossValidator");

    private final ServerService serverService;
    private final ModuleValidator moduleValidator;
    private final MessageService messageService;

    @Autowired
    public CrossValidator(ServerService serverService, ModuleValidator moduleValidator, MessageService messageService) {
        this.serverService = serverService;
        this.moduleValidator = moduleValidator;
        this.messageService = messageService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Cross.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        logger.info("Inside validate ....");
        Cross cross = (Cross) obj;
        validateCrossName(cross, errors);
        validateModules(cross, errors);
    }

    public void validateCrossName(Cross cross, Errors errors) {
        String validateField = "name";
        String name = cross.getName();
        // Empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, validateField,
                messageService.getMessage("cross.name.notNull", null));
        if (errors.getFieldError(validateField) != null) {
            return;
        }
        // Length
        int maxLengthName = 50;
        int minLengthName = 2;
        int crossNameLength = name.length();
        if (crossNameLength > maxLengthName) {
            errors.rejectValue(validateField, messageService.getMessage("cross.name.maxSize", new Object[]{maxLengthName}));
            return;
        } else if (crossNameLength < minLengthName) {
            errors.rejectValue(validateField, messageService.getMessage("cross.name.minSize", new Object[]{minLengthName}));
            return;
        }
        // Duplicate
        Long serverId = cross.getServer().getId();
        for (Cross duplicateCross : serverService.findById(serverId).getCrosses()) {
            if (name.equals(duplicateCross.getName()) && !cross.getId().equals(duplicateCross.getId())) {
                errors.rejectValue(validateField, messageService.getMessage("cross.name.inUse", null));
                return;
            }
        }
    }

    public void validateModules(Cross cross, Errors errors) {
        String validateField = "amountModuleSlots";
        //Slots
        if (cross.getAmountModuleSlots() <= 0) {
            errors.rejectValue(validateField, messageService.getMessage("cross.modules.positive", null));
        } else if (cross.getEmptyModuleSlots() < 0) {
            errors.rejectValue(validateField,
                    messageService.getMessage("cross.modules.limit", new Object[]{cross.getAmountModuleSlots()}));
        }
        //Duplicate
        Collection<Module> moduleCollection = cross.getModules();
        Set<Integer> moduleNumbers = new HashSet<>();
        moduleCollection.forEach(m -> moduleNumbers.add(m.getNumber()));
        if (moduleCollection.size() > moduleNumbers.size()) {
            errors.rejectValue("modules", messageService.getMessage("module.number.inUse", null));
        }
        //Module fields
        for (Module module : cross.getModules()) {
            moduleValidator.validate(module, errors);
        }
    }
}
