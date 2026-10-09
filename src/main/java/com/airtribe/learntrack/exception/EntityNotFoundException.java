package com.airtribe.learntrack.exception;

import java.io.Serializable;

public class EntityNotFoundException extends Exception implements Serializable {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
