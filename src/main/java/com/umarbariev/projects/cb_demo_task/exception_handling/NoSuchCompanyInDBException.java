package com.umarbariev.projects.cb_demo_task.exception_handling;

public class NoSuchCompanyInDBException extends RuntimeException {
    public NoSuchCompanyInDBException(String message) {
        super(message);
    }
}
