package com.library.service.exception;

public class ResourceNotFoundException extends RuntimeException {
    private Object resourceId;

    public ResourceNotFoundException(Object resourceId) {
        super(String.format("Resource not found for given id : %s ", resourceId));
        this.resourceId = resourceId;
    }
}
