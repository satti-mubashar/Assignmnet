package com.employee.crud.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ResourceCreationException extends RuntimeException  {

    private static final Logger logger = LogManager.getLogger(ResourceCreationException.class);

    private final String name;
    private final String resource;

    public ResourceCreationException(String name, String resource) {
        super(String.format("Failed to create [%s] for resource  : '%s'", name, resource));
        this.name = name;
        this.resource = resource;
        logger.info(String.format("Failed to create [%s] for resource  : '%s'", name, resource));
    }

    public ResourceCreationException(String message) {
        super(String.format(message));
        this.name = "";
        this.resource = "";
        logger.info(String.format(message));
    }

}