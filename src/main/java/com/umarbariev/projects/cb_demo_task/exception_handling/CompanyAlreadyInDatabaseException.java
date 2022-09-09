package com.umarbariev.projects.cb_demo_task.exception_handling;

public class CompanyAlreadyInDatabaseException extends RuntimeException {
    public CompanyAlreadyInDatabaseException(String message) {
        super(message);
    }
}
