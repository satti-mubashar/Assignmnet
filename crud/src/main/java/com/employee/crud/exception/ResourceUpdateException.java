package com.employee.crud.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourceUpdateException extends RuntimeException {

    private static final Logger logger = LogManager.getLogger(ResourceUpdateException.class);

    public ResourceUpdateException(String message) {
        super(message);
        logger.info(message);
    }

    public ResourceUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}