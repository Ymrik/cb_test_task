package com.umarbariev.projects.cb_demo_task.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class InsuranceCompanyExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionInfo> handleWrongParameterException(WrongParameterException exception) {
        ExceptionInfo incorrectData = new ExceptionInfo();
        String exceptionInfo = exception.getMessage();
        incorrectData.setInfo(exceptionInfo);
        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<InsuranceCompanyIncorrectData> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        InsuranceCompanyIncorrectData incorrectData = new InsuranceCompanyIncorrectData();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        incorrectData.setErrors(errors);
        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionInfo> handleNoSuchCompanyException(NoSuchCompanyInDBException exception) {
        ExceptionInfo incorrectData = new ExceptionInfo();
        String exceptionInfo = exception.getMessage();
        incorrectData.setInfo(exceptionInfo);
        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionInfo> handleCompanyAlreadyInDBException(CompanyAlreadyInDatabaseException exception) {
        ExceptionInfo incorrectData = new ExceptionInfo();
        String exceptionInfo = exception.getMessage();
        incorrectData.setInfo(exceptionInfo);
        return new ResponseEntity<>(incorrectData, HttpStatus.CONFLICT);
    }
}
