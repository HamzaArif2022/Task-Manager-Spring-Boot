package com.taskmanager.taskmanager.exception;

public class ResourceNotFoundException extends  RuntimeException {

    ResourceNotFoundException(String message) {
        super(message);
    }
}
