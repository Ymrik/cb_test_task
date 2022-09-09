package com.umarbariev.projects.cb_demo_task.exception_handling;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Map;

@Data
public class InsuranceCompanyIncorrectData {
    @Setter(value = AccessLevel.NONE)
    private String message = "Wrong input data for InsuranceCompany object.";
    private Map<String, String> errors;
}
