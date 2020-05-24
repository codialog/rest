package com.treetrunk.trek.validator;

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

    Logger logger = Logger.getLogger("Class ModuleValidator");

    private final MessageService messageService;

    public ModuleValidator(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Module.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        logger.info("Inside validate ....");
        Module module = (Module) obj;
        validateModuleNumber(module, errors);
        validatePorts(module, errors);
    }

    public void validateModuleNumber(Module module, Errors errors) {
        String validateField = "modules";
        // Empty
        if (module.getNumber() == null) {
            errors.rejectValue(validateField, messageService.getMessage("module.number.notNull", null));
        } else if (module.getNumber() < 0) {
            errors.rejectValue(validateField, messageService.getMessage("module.number.positive", null));
        } else if (module.getNumber() > module.getCross().getAmountModuleSlots()) {
            errors.rejectValue(validateField,
                    messageService.getMessage("module.number.limit", new Object[]{module.getCross().getAmountModuleSlots()}));
        }
    }

    public void validatePorts(Module module, Errors errors) {
        String validateField = "modules";
        //Slots
        if (module.getAmountPortSlots() <= 0) {
            errors.rejectValue(validateField,
                    messageService.getMessage("module.ports.positive", null));
        } else if (module.getEmptyPortSlots() < 0) {
            errors.rejectValue(validateField,
                    messageService.getMessage("module.ports.limit", new Object[]{module.getNumber(), module.getAmountPortSlots()}));
        }
        //Duplicate
        Collection<Port> portCollection = module.getPorts();
        Set<Integer> portNumbers = new HashSet<>();
        portCollection.forEach(p -> portNumbers.add(p.getNumber()));
        if (portCollection.size() > portNumbers.size()) {
            errors.rejectValue(validateField,
                    messageService.getMessage("port.number.inUse", new Object[]{module.getNumber()}));
        }
        //Number
        //Empty
        portNumbers.forEach(n -> {
            if (n == null) {
                errors.rejectValue(validateField,
                        messageService.getMessage("port.number.notNull", null));
            } else if (n < 0) {
                errors.rejectValue(validateField, messageService.getMessage("port.number.positive", null));
            } else if (n > module.getAmountPortSlots()) {
                errors.rejectValue(validateField, messageService.getMessage("port.number.limit", new Object[]{module.getAmountPortSlots()}));
            }
        });
    }
}
