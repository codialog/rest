package com.treetrunk.trek.validator;

import com.treetrunk.trek.model.Server;
import com.treetrunk.trek.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.logging.Logger;

@Service
public class ServersValidator implements Validator {

    Logger logger = Logger.getLogger("Class ServersValidator");

    private final ServerService serverService;
    private final MessageService messageService;

    @Autowired
    public ServersValidator(ServerService serverService, MessageService messageService) {
        this.serverService = serverService;
        this.messageService = messageService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Server.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        logger.info("Inside validate ....");
        Server server = (Server) obj;
        validateServerName(server, errors);
    }

    public void validateServerName(Server server, Errors errors) {
        // Empty
        String validateField = "name";
        String name = server.getName();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, validateField,
                messageService.getMessage("server.name.notNull", null));
        if (errors.getFieldError(validateField) != null) {
            return;
        }
        // Length
        Integer maxLengthName = 100;
        Integer minLengthName = 1;
        Integer serverNameLength = name.length();
        if (serverNameLength > maxLengthName) {
            errors.rejectValue(validateField,
                    messageService.getMessage("server.name.maxSize", new Object[]{maxLengthName}));
            return;
        } else if (serverNameLength <= minLengthName) {
            errors.rejectValue(validateField,
                    messageService.getMessage("server.name.minSize", new Object[]{minLengthName}));
            return;
        }
        // Duplicate
        Server duplicatedServer = serverService.findByName(name);
        if (duplicatedServer != null && server.getAddress().equals(duplicatedServer.getAddress())) {
            errors.rejectValue(validateField, messageService.getMessage("server.name.inUse", null));
        }
    }
}
