package com.treetrunk.trek.validators;

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

    Logger logger = Logger.getLogger("Class CrossFormValidator");
    private final ServerService serverService;

    @Autowired
    public ServersValidator(ServerService serverService) {
        this.serverService = serverService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Server.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        logger.info("Inside create validate ....");
        Server server = (Server) obj;
        validateServerName(server.getName(), errors);
    }

    public void validateServerName(String name, Errors errors) {
        // Empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Server name is a required field");
        if (errors.getFieldError("name") != null) {
            return;
        }
        // Length
        int maxLengthName = 100;
        int minLengthName = 2;
        int serverNameLength = name.length();
        if (serverNameLength > maxLengthName) {
            errors.rejectValue("name", "Server name should be shorter then " + maxLengthName + " characters");
            return;
        } else if (serverNameLength < minLengthName) {
            errors.rejectValue("name", "Server name should be longer than " + minLengthName + " characters");
            return;
        }
        // Duplicate
        Server duplicatedServer = serverService.findByName(name);
        if (duplicatedServer != null) {
            errors.rejectValue("name", "Server name is already in use");
        }
    }


}
