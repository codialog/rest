package com.treetrunk.trek.validators;

import com.treetrunk.trek.model.Module;
import com.treetrunk.trek.model.Port;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class ModuleValidator implements Validator {

    Logger logger = Logger.getLogger("Class CrossModulesValidator");

    @Override
    public boolean supports(Class<?> aClass) {
        return Module.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        logger.info("Inside create validate ....");
        Module module = (Module) obj;
        validateModuleNumber(module, errors);
        validatePorts(module, errors);
    }

    public void validateModuleNumber(Module module, Errors errors) {
        //Length
        if (module.getNumber() < 0) {
            errors.rejectValue("number", "Module number should be more then 0");
        }
    }

    public void validatePorts(Module module, Errors errors) {
        //Slots
        if (module.getAmountPortSlots() <= 0) {
            errors.rejectValue("modules",
                    "Amount slots of ports should be more then 0");
        } else if (module.getEmptyPortSlots() < 0) {
            errors.rejectValue("modules",
                    "Amount slots of ports in module '" + module.getNumber() + "' more then limit: " + module.getAmountPortSlots());
        }
        //Duplicate
        Collection<Port> portCollection = module.getPorts();
        Set<Integer> portNumbers = new HashSet<>();
        portCollection.forEach(p -> portNumbers.add(p.getNumber()));
        if (portCollection.size() > portNumbers.size()) {
            errors.rejectValue("modules",
                    "Numbers of port in module' " + module.getNumber() + "' are duplicated");
        }
        //Number
        if (portNumbers.stream().anyMatch(n -> n < 0)) {
            errors.rejectValue("number", "Module number should be more then 0");
        }
    }
}
