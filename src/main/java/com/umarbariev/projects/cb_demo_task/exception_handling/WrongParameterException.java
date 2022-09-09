package com.umarbariev.projects.cb_demo_task.exception_handling;

public class WrongParameterException extends RuntimeException {
    public WrongParameterException(String message) {
        super(message);
    }
}
