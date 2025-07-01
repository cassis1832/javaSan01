package com.holis.san01.exceptions;

import java.util.List;

public class ResourceConflictException extends RuntimeException {
    private final String resourceName;
    private final Object resourceId;
    private final List<String> conflicts;

    public ResourceConflictException(String resourceName, Object resourceId, List<String> conflicts) {
        super(String.format("%s with ID %s cannot be deleted due to conflicts", resourceName, resourceId));
        this.resourceName = resourceName;
        this.resourceId = resourceId;
        this.conflicts = conflicts;
    }
}
