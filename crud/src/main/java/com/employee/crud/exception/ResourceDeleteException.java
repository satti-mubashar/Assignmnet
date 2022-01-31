package com.employee.crud.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourceDeleteException extends RuntimeException {

    private static final Logger logger = LogManager.getLogger(ResourceDeleteException.class);

    public ResourceDeleteException(String message) {
        super(message);
        logger.info(message);
    }

    public ResourceDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}